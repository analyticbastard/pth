(defproject analyticbastard/pth "0.1.0-SNAPSHOT"
  :description "Parallel thread flows in clojure, a couple of macros that evaluate the given expressions on an input and produce a list of results"
  :url ""
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}
             :dev {:dependencies [[org.clojure/tools.namespace "0.2.11"]]}})
