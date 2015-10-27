(ns pth)

(defmacro -< [& forms]
  (let [placeholder (-> forms butlast last)
        [x forms] (if (= :_ placeholder) [(last forms) (-> forms butlast butlast)]
                                         [(first forms) (next forms)])]
    (loop [prev (if-not forms [x] [])
           forms forms]
      (if forms
        (let [form (first forms)
              current (if (seq? form)
                        (with-meta `(~(first form) ~x ~@(next form)) (meta form))
                        (list form x))]
          (recur (conj prev current) (next forms)))
        prev))))

(defmacro -<< [& forms]
  (let [placeholder (second forms)
        [x forms] (if (= :_ placeholder) [(first forms) (-> forms rest rest)]
                                         [(last forms) (butlast forms)])]
    (loop [prev (if-not forms [x] [])
           forms forms]
      (if forms
        (let [form (first forms)
              current (if (seq? form)
                        (with-meta `(~(first form) ~@(next form) ~x) (meta form))
                        (list form x))]
          (recur (conj prev current) (next forms)))
        prev))))