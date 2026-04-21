#!/usr/bin/env node

const os = require('os');
const path = require('path');
const { spawn } = require('child_process');

const platform = os.platform();
const arch = os.arch();

let binaryName = 'lnw-cli';
if (platform === 'win32') {
    binaryName += '.exe';
}

// Map platform/arch to specific binary paths if needed
// For now we assume they are in the binaries folder
const binaryPath = path.join(__dirname, '..', 'binaries', platform, arch, binaryName);

const child = spawn(binaryPath, process.argv.slice(2), {
    stdio: 'inherit'
});

child.on('error', (err) => {
    console.error(`Failed to start binary: ${err.message}`);
    process.exit(1);
});

child.on('exit', (code) => {
    process.exit(code);
});
