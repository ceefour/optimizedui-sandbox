function fetchCategories(success) {
	console.info('Fetching categories...');
	var categoryRepo = require('./category.js');
	success(categoryRepo.categories);
}

function fetchContent(success) {
	console.info('Fetching content...');
	var content = '<h1>Latest Fashion</h1><p>You will like this very much!</p>';
	success(content);
}

function checkHomePage(homePage) {
	if (homePage.headerView != undefined && homePage.contentView != undefined) {
		homePage.response.end(homePage.headerView + homePage.contentView);
	}
}

var http = require('http');
http.createServer(function (req, res) {
	res.writeHead(200, {'Content-Type': 'text/html'});
	var homePage = {response: res};
	// This is the basic idea, but it doesn't execute asynchronously... yet
	fetchCategories(function(categories) {
		console.info('Categories:', categories);
		var output = '<ul>';
		for (cat in categories) {
			output += '<li>' + categories[cat] + '</li>';
		}
		output += '</ul>';
		homePage.headerView = output;
		checkHomePage(homePage);
	});
	fetchContent(function(content) {
		console.info('Content:', content);
		var output = '<div id="content">' + content + '</div>';
		homePage.contentView = output;
		checkHomePage(homePage);
	});
	
}).listen(1338, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1338/');
