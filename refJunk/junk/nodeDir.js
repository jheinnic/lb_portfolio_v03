traverse = require('./../common/components/fsutil/traverse');
function findNodeModules(dirPath, onEntry) {
	if( onEntry ) {
		if( dirPath.match(/node_modules$/) ) {
			console.log(dirPath);
			return false;
		}
	}
}

traverse('.', undefined, findNodeModules);
