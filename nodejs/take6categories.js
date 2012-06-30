/*
Requires nginx.

Create a block in nginx main server (/etc/nginx/sites-available/default) like below:

server {
	...
	
	# ouinode
	location /ouinode {
		proxy_pass http://127.0.0.1:1338;
	}
	location /ouinode_static {
		alias /home/ceefour/git/optimizedui-sandbox/nodejs/static;
		autoindex on;
	}

}

Performance
- template is loaded on-demand: 647.93 r/s
- template preloaded: 714.03 r/s
Seems like Linux filesystem cache/buffer is working well. :)

*/

var _ = require('./lib/underscore.js');
var http = require('http');
var fs = require('fs');

function fetchCategories(success) {
	console.log('Fetching categories...');
	process.nextTick(function() {
		var categoryRepo = require('./category.js');
		success(categoryRepo.categories);
	});
}

function fetchContent(success) {
	console.log('Fetching content...');
	process.nextTick(function() {
		var content = '<h1>Latest Fashion</h1><p>You will like this very much!</p>';
		success(content);
	});
}

var homePageTemplate = fs.readFileSync('templates/layout.html.tpl', 'UTF-8');

function checkHomePage(homePage) {
	if (homePage.headerView != undefined && homePage.contentView != undefined && homePage.sidebarView != undefined) {
		console.log('Page has all elements, rendering response');
		//homePage.response.end(homePage.headerView + homePage.contentView);
		fs.readFile('templates/layout6.html.tpl', 'UTF-8', function(err, data) {
			if (err) throw err;
			var output = _.template(data, {
				baseUri: '/ouinode/',
				staticUri: '/ouinode_static/',
				jsUri: '/ouinode_static/js/',
				skinUri: '/ouinode_static/skin/',
				mediaUri: '/ouinode_static/media/',
				pageTitle: 'Buy best fashion',

				sidebarView: homePage.sidebarView,
			});
			homePage.response.end(output);
		});
	} else {
		console.log('Checking page but page is not complete');
	}
}

http.createServer(function (req, res) {
	res.writeHead(200, {'Content-Type': 'text/html'});
	var homePage = {response: res};
	fetchCategories(function(categories) {
		console.log('Categories:', categories);
		output = _.template('<ul> \
		<% _.each(categories, function(category) { %> \
			<li><%- category %></li> \
		<% }) %> \
		</ul>', {categories: categories});
		homePage.headerView = output;
		homePage.sidebarView = output;
		checkHomePage(homePage);
	});
	fetchContent(function(content) {
		console.log('Content:', content);
		var output = _.template('<div id="content"><%= content %></div>', {content: content});;
		homePage.contentView = output;
		checkHomePage(homePage);
	});
	console.log('Request handler done! Response will be sent later.');
}).listen(1338, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1338/');
