(ns pth)

(defmacro -< [x & forms]
  (loop [prev (if-not forms [x] [])
         forms forms]
    (if forms
      (let [form (first forms)
            current (if (seq? form)
                       (with-meta `(~(first form) ~x ~@(next form)) (meta form))
                       (list form x))]
        (recur (conj prev current) (next forms)))
      prev)))

(defmacro -<< [x & forms]
  (loop [prev (if-not forms [x] [])
         forms forms]
    (if forms
      (let [form (first forms)
            current (if (seq? form)
                      (with-meta `(~(first form) ~@(next form) ~x) (meta form))
                      (list form x))]
        (recur (conj prev current) (next forms)))
      prev)))