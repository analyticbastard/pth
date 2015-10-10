# pth
Parallel thread flows in clojure, a couple of macros that evaluate the given expressions on an input and produce a list of results

## Usage


```clojure
(-<< [1 2 3]
     (map #(* 2 %))
     (map #(+ 1 %)))

=> [(2 4 6) (2 3 4)]
```

```clojure
(let [my-map1 {:1 1, :2 2, :3 3}
      my-map2 {:1 5, :2 6, :3 7}
(->> [:1 :2]
     (-<< (map my-map1)
          (map my-map2))
  ))

=> [(1 2) (5 6)]
```



## License

Copyright © 2015 Javier Arriero

[Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html)

