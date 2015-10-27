(ns pth-example.core
  (use pth)
  (:gen-class))

(defn -main
  [& args]
  (let [map1 {:1 :a :2 :b :3 :c}
        map2 {:1 1 :2 2 :3 3}
        ]
    (->> [:1 :2]
         (-<< (map map1)
              (map map2))
         (apply interleave)
         (apply hash-map)
         )))
