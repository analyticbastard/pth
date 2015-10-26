(ns pth-test
  (:use pth)
  (:require [clojure.test :refer :all]))

(deftest test-with-no-forms
  (testing "Testing evaluation with no forms"
    (let [input-value 1]
      (is (= (-< input-value) [input-value]))
      (is (= (-<< input-value) [input-value])))))

(deftest test-pre-thread
  (testing "Testing evaluation with no forms"
    (let [input-value :1
          my-map1 {:1 1, :2 2, :3 3}
          my-map2 {:1 5, :2 6, :3 7}]
      (is (= (-< input-value
                 identity
                 my-map1
                 my-map2)
             [input-value (my-map1 input-value) (my-map2 input-value)])))))

(deftest test-post-thread
  (testing "Testing evaluation with no forms"
    (let [input-value [:1 :2]
          my-map1 {:1 1, :2 2, :3 3}
          my-map2 {:1 5, :2 6, :3 7}]
      (is (= (-<< (map my-map1)
                  (map my-map2)
                  input-value)
             ['(1 2) '(5 6)])))))

(deftest test-post-thread-placeholder
  (testing "Testing placeholder"
    (let [input-value [:1 :2]
          my-map1 {:1 1, :2 2, :3 3}
          my-map2 {:1 5, :2 6, :3 7}]
      (is (= (-> input-value
                 (-<< :_
                      (map my-map1)
                      (map my-map2)))
             ['(1 2) '(5 6)])))))

(comment
  (clojure.test/run-all-tests)
  )