// with console.log : ab -c8 -n10000 http://localhost:1338/ gives 2180 r/s on i7-2630QM
// without console.log : ab -c8 -n10000 http://localhost:1338/ gives 7476 r/s on i7-2630QM

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

function checkHomePage(homePage) {
	if (homePage.headerView != undefined && homePage.contentView != undefined) {
		console.log('Page has all elements, rendering response');
		homePage.response.end(homePage.headerView + homePage.contentView);
	} else {
		console.log('Checking page but page is not complete');
	}
}

var _ = require('./lib/underscore.js');

var http = require('http');
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
