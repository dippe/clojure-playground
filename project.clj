(defproject learn "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 [org.clojure/clojure "1.10.0"]
                 [criterium "0.4.4"]
                 [iota "1.1.3"]
                 [squeezer "0.4.0"]
                 [tesser.core "1.0.3"]
                 [org.clojure/tools.trace "0.7.10"]
                 ]
  :main learn.core
  ;:aot [learn.core]

  :repl-options {:init-ns learn.core}

  :profiles
  {:uberjar {:omit-source  false
             :env          {:production true}
             :aot          :all
             :source-paths ["src"]}
   }
  )
