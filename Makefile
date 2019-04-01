run_all_in_parallel:
	make clean_it test_parallel

clean_it:
	mvn clean

test_parallel:
	make -j test_chrome test_firefox

test_chrome:
	mvn install -Dbrowser=chrome -Pprod -Dsuitename=test_Chrome -Dcucumber.options="--plugin json:target/cucumber/test_chrome/chrome.json --tags @Title"

test_firefox:
	mvn install -Dbrowser=firefox -Pprod -Dsuitename=test_firefox -Dcucumber.options="--plugin json:target/cucumber/test_firefox/firefox.json --tags @Title"

