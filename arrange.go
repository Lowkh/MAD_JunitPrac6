package main

import (
	"fmt"
	"io"
	"log"
	"os"
	"path/filepath"
)

var absPath string // global variable to store absolute path of filename.

func findFileAbsPath(basepath, filename string) (string, error) {
	// This function finds the absolute path of specified filename, returns the absolute path filename and error.
	// The function takes in a base path to search from, filepath.Walk searches file recursively.
	fileErr := filepath.Walk(basepath, func(path string, info os.FileInfo, err error) error {
		// path is the absolute path.
		if err != nil {
			return err
		}
		if info.Name() == filename {
			// if the filename is found return the absolute path of the file.
			absPath = path
		}
		return nil
	})
	if fileErr != nil {
		return "", fileErr
	}
	return absPath, nil
}

func main() {
	// find path by example file.
	var err error
	absPath, err = findFileAbsPath("../app", "ExampleInstrumentedTest.java")
	if err != nil {
		log.Fatal(err)
	}
	//retrieve path location
	realPathAnd := absPath[:len(absPath)-28]
	fmt.Println(realPathAnd)

	//copy test files to location.
	fromAnd, err := os.Open("../testDirectory/ListActivityTest.java")
	if err != nil {
		log.Fatal(err)
	}
	defer fromAnd.Close()

	toAnd, err := os.OpenFile(realPathAnd+"ListActivityTest.java", os.O_RDWR|os.O_CREATE, 0666)
	if err != nil {
		log.Fatal(err)
	}
	defer toAnd.Close()

	_, err = io.Copy(toAnd, fromAnd)
	if err != nil {
		log.Fatal(err)
	}

	fromAnd, err = os.Open("../testDirectory/DBHandlerTest.java")
	if err != nil {
		log.Fatal(err)
	}
	defer fromAnd.Close()

	toAnd, err = os.OpenFile(realPathAnd+"DBHandlerTest.java", os.O_RDWR|os.O_CREATE, 0666)
	if err != nil {
		log.Fatal(err)
	}
	defer toAnd.Close()

	_, err = io.Copy(toAnd, fromAnd)
	if err != nil {
		log.Fatal(err)
	}

	fromAnd, err = os.Open("../testDirectory/MainActivityIntentTest.java")
	if err != nil {
		log.Fatal(err)
	}
	defer fromAnd.Close()

	toAnd, err = os.OpenFile(realPathAnd+"MainActivityIntentTest.java", os.O_RDWR|os.O_CREATE, 0666)
	if err != nil {
		log.Fatal(err)
	}
	defer toAnd.Close()

	_, err = io.Copy(toAnd, fromAnd)
	if err != nil {
		log.Fatal(err)
	}
}
