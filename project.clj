(defproject filldays "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "GNU General Public License (GPL) version 3"
            :url "https://www.gnu.org/licenses/gpl.html"}
  :dependencies [[org.clojure/clojure "1.8.0"] [clj-time "0.13.0"]]
  :main ^:skip-aot filldays.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
