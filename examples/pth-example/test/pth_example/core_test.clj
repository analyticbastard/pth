(ns pth-example.core-test
  (:require [clojure.test :refer :all]
            [pth-example.core :refer :all]))

(deftest a-test
  (testing "Use values of an array as keys for values of another array"
    (is (= (pth-example.core/-main) {:a 1 :b 2}))))
