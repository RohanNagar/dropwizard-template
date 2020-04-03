# Thunder Scripts

This directory contains a number of scripts to ease development and enable integration testing.

- [Getting Started](#getting-started)
- [Explanation of Directories](#explanation-of-directories)
- [Available Scripts](#available-scripts)
- [Testing with Docker](#testing-with-docker)
- [Writing New Integration Tests](#writing-new-integration-tests)

## Getting Started

First, make sure you have Node.js and NPM installed.

Second, install the required NPM packages.

```bash
$ npm install
```

Now you're set to run any of the available scripts.

If you want to run tests using docker-compose, make sure to install `docker` and `docker-compose`.

## Explanation of Directories

* `ci` - This holds scripts used by CI, such as pushing a Docker image or running multiple integration tests.
* `deploy` - This holds scripts and templates that can be used to deploy the application and related resources.
* `lib` - This is source code that is used in the `tools` scripts. All code is written in Node.js.
* `tests` - This holds the integration test runner script along with a directory for each integration test.
To add a new test, create a new directory with that test name and add the relevant files: `config.yaml`,
`docker-compose.yml`, and `tests.yaml`
* `tools` - This holds scripts that improve development life, such as updating specific dependencies,
running local dependencies, or running integration tests.

## Available Scripts
