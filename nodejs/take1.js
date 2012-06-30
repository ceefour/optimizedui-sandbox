function fetchCategories(success) {
	console.info('Fetching categories...');
	var categoryRepo = require('./category.js');
	success(categoryRepo.categories);
}

var http = require('http');
http.createServer(function (req, res) {
	res.writeHead(200, {'Content-Type': 'text/html'});
	fetchCategories(function(categories) {
		console.info('Categories:', categories);
		var output = '<ul>';
		for (cat in categories) {
			output += '<li>' + categories[cat] + '</li>';
		}
		output += '</ul>';
		res.end(output);
	});
}).listen(1338, '127.0.0.1');
console.log('Server running at http://127.0.0.1:1338/');
