(ns pth-test
  (:use pth)
  (:require [clojure.test :refer :all]))

(deftest test-with-no-forms
  (testing "Testing evaluation with no forms"
    (let [input-value 1]
      (is (= (-< input-value) input-value))
      (is (= (-<< input-value) input-value)))))


(comment
  (clojure.test/run-all-tests)
  )