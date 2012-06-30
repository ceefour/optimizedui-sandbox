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

With concurrency 128 and categories is delayed 1000ms:

Requests per second:    117.65 [#/sec] (mean)
Time per request:       1088.014 [ms] (mean)
Time per request:       8.500 [ms] (mean, across all concurrent requests)

With concurrency 256 and categories is delayed 1000ms:

Requests per second:    212.57 [#/sec] (mean)
Time per request:       1204.327 [ms] (mean)
Time per request:       4.704 [ms] (mean, across all concurrent requests)

So even under many concurrent load, RPS is good.
Problemm is, time per request is bad.

*/

var _ = require('./lib/underscore.js');
var http = require('http');
var fs = require('fs');
var categoryRepo = require('./category.js');

function fetchCategories(success) {
	console.log('Fetching categories, simulating load by waiting 1000ms...');
	setTimeout(function() {
		success(categoryRepo.categories);
	}, 1000);
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

				headerView: homePage.headerView,
				sidebarView: homePage.sidebarView,
				contentView: homePage.contentView,
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
	homePage.headerView = '					<ul class="nav">\
						<li><p class="navbar-text"><span id="notification-count">0</span> &nbsp;</p></li>\
						<li class="active"><h:link outcome="pretty:home" value="Home" /></li>\
						<li><a href="#about">About</a></li>\
						<li><a href="#contact">Contact</a></li>\
					</ul>';

	fetchCategories(function(categories) {
		console.log('Categories:', categories);
		output = _.template('<ul> \
		<% _.each(categories, function(category) { %> \
			<li><%- category %></li> \
		<% }) %> \
		</ul>', {categories: categories});
		homePage.sidebarView = output;
		checkHomePage(homePage);
	});
	fetchContent(function(content) {
		console.log('Content:', content);
		var output = _.template('<div id="content"><%= content %></div>', {content: content});
		homePage.contentView = output;
		checkHomePage(homePage);
	});
	console.log('Request handler done! Response will be sent later.');
}).listen(1338, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1338/');
