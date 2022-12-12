// This script should  move all local branches with no unmerged changes forward
// to the latest 'main', and then push them

const { readFile, writeFile } = require ('fs/promises');

const files = [
  'ForTeaching/build.gradle',
  'Sixteen750/build.gradle',
  'Twenty403/build.gradle',
  'build.dependencies.gradle',
  'settings.gradle',
];

// For any line that ends with '// TechnoLibLocal', toggle the line comment marker
function toggleLine(lineFull) {
  const line = lineFull.trimEnd();
  if (line.endsWith('// TechnoLibLocal')) {
    if (line.trimStart().startsWith('//')) {
      // Remove the comment marker
      return line.replace(/^( *)\/\/ */, '$1');
    } else {
      // Add the comment marker
      return line.replace(/^( *)([^ ])/, '$1// $2');
    }
  }
  return line;
}

// Read the file, flipping the comments for lines with markers
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
  .catch((err) => console.error(err));
