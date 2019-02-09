# mck.app

A example project using clojure-cli, Figwheel-main, Devcards, Reagent and Re-frame.
(To be) Used at ClojureSEPL (a local meetup).

Start Figwheel and browse to: http://localhost:9500/devcards.html

## Development

To get an interactive development environment run:

    clojure -A:fig:build

This will auto compile and send all changes to the browser without the
need to reload. After the compilation process is complete, you will
get a Browser Connected REPL. An easy way to try it is:

    (js/alert "Am I connected?")

and you should see an alert in the browser window.

Go to http://localhost:9500/devcards.html to view the actual code running.

To clean all compiled files:

    rm -rf target/public

To create a production build run:

	rm -rf target/public
	clojure -A:fig:min
