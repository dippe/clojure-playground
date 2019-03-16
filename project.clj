(defproject hello-world "0.1.0-SNAPSHOT"
  :description "My first clj prj"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 [org.clojure/clojure "1.10.0"]
                 [criterium "0.4.4"]
                 ]
  :repl-options {:init-ns hello-world.core}
  :main hello.world
  )

