# pth

[![Build Status](https://travis-ci.org/analyticbastard/pth.svg?branch=master)](https://travis-ci.org/analyticbastard/pth)

Parallel thread flows in clojure, a couple of macros that evaluate the given expressions on an input and produce a list of results

It is especially designed to cope with the case when more than one evaluation of the previous value is required in a Clojure thread flow,
and the syntax is cleaner than ``as->`` since the passed values are hidden. The form following the parallel thread flow must
know the layout of the vector in order to process it, of course.


## Installation

Add this to your ```project.clj```

```clojure
[analyticbastard/pth "0.1.0-SNAPSHOT"]
```

## Usage

Require the library's main namespace from your namespace:

```clojure
(:require pth)
```

Then use it:

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

