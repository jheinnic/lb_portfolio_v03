//document.write('<script src="http://'
//+ window.location.hostname
//+ ':35729/livereload.js?snipver=1" type="text/javascript"><\/script>');

var scriptElem = document.createElement('script');
scriptElem.src = 'http://' + window.location.hostname + ':35729/livereload.js?snipver=1&host=localhost&hostname=localhost';
scriptElem.type = 'text/javascript';
document.body.appendChild(scriptElem);
