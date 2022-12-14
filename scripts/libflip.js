/*
 * This script should invert all comment lines in the build system with a
 * '// TechnoLibLocal' comment at the end. This will let you add a subdirectory
 * "TechnoLib" (as a git subtree, submodule, or just a copy) that contains
 * TechnoLib instead of pulling down the latest release from jitpack.io through
 * Maven or whatever that remote repo is.
 *
 * So, "yarn libflip" will, from a normal repo, enable you to build & link with
 * a local copy of TechnoLib in <root>\TechnoLib. Once you're done, run
 * "yarn libflip" again, and it will restore the dependency on the publicly
 * released copy of TechnoLib
 */

const { readFile, writeFile } = require('fs/promises');

const commentMarker = '// TechnoLibLocal';
const files = [
  'ForTeaching/build.gradle',
  'Sixteen750/build.gradle',
  'Swerveteen750/build.gradle',
  'Twenty403/build.gradle',
  'build.dependencies.gradle',
  'settings.gradle',
];

// For any line that ends with '// TechnoLibLocal',
// toggle the line comment 'status'
function toggleLine(lineFull) {
  const line = lineFull.trimEnd();
	// If the line doesn't end with the comment marker, don't change it at all
  if (!line.endsWith(commentMarker)) {
    return line;
  }
  if (line.trimStart().startsWith('//')) {
		// If the line starts with a comment, 
		// remove the comment at the beginning of the line
    return line.replace(/^( *)\/\/ */, '$1');
  } else {
		// If the line does *not* start with a comment, 
    // add the comment at the beginning of the line
    return line.replace(/^( *)([^ ])/, '$1// $2');
  }
}

// Read the file, flip the comments for lines with markers, the write it back
async function toggleFile(file) {
  try {
    const contents = await readFile(file, 'utf-8');
    const resultArray = contents.split('\n');
    const toggled = resultArray.map(toggleLine);
    await writeFile(file, toggled.join('\n'));
  } catch (e) {
    // Some file access problem :(
    console.error('Problem with dealing with file:', file);
    console.error(e);
  }
}

async function toggleLinesWithComments() {
  for (let filename of files) {
    await toggleFile(filename);
  }
}

// Call our script...
toggleLinesWithComments()
  // Reporting errors
  .catch((err) => console.error(err))
	.finally(() => console.log("Processing complete"));
