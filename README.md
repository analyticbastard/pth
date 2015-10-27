# pth

[![Build Status](https://travis-ci.org/analyticbastard/pth.svg?branch=master)](https://travis-ci.org/analyticbastard/pth)

Parallel thread flows in clojure, a couple of macros that evaluate the given expressions on an input and produce a list of results

It is especially designed to cope with the case when more than one evaluation of the previous value is required in a Clojure thread flow,
and the syntax is cleaner than ``as->`` since the passed values are hidden. The form following the parallel thread flow must
know the layout of the vector in order to process it, of course.


## Parallel thread flows

In a Clojure flow, we sometimes need to compute several results based on the value of the flow at a particular point, which
forces us to interrupt the flow by using a ```let``` form or introduce weird evaluations of a customized inline function.

pth makes this case idiomatic and allows us not to break the flow by introducing two new macros, which follow their ```clojure.core```
in the order of arguments they accept:

* ```-<``` (parallel thread first) accepts arguments in its first position, as ```->```
* ```-<<``` (parallel thread last) accepts arguments in its last position, as ```->>```

Therefore, ```-< 1 inc (/ 3)``` evaluates both forms with 1 as first argument, resulting in ```[2 1/3]```, and ```-<< inc (/ 3) 1```
evaluates both forms with 1 as last argument, resulting in ```[2 3]```.

This is especially suitable to use within Clojure threads:

``` clojure
(let [input-value [:1 :2]
      my-map1 {:1 1, :2 2, :3 3}
      my-map2 {:1 5, :2 6, :3 7}]
      (-> input-value
          first
          (-< identity
              my-map1
              my-map2)
          (-< first
              rest)))
=> [:1 (1 5)]
```

We see that the values for both maps can be recovered within the Clojure thread.


### Placeholders

The library also provides useful functionality to rearrange the order of the input parameters by using placeholders.
For example, ```-<<``` accepts the input in its last argument, but if we use the placeholder ```:_``` in the first position,
then it will use the first form it finds as value. In the next example, we use a thread first to pass values forward as in
the previous example, where we want to get the values for the key ```:1``` in two maps, but this time we want to create a
new map containing the key and a list of values. For that, we need to use ```(apply hash-map)```, but that would force us to
break the thread first flow, since the first parameter to apply must be a function, not the value we are passing on, which
must go at the last position. The solution is to use a parallel thread last macro with a placeholder to receive the input
value as the first argument. This will switch the order and place the input value as the last argument to any inner form.

``` clojure
(let [input-value [:1 :2]
      my-map1 {:1 1, :2 2, :3 3}
      my-map2 {:1 5, :2 6, :3 7}]
  (-> input-value
      first
      (-< identity
          my-map1
          my-map2)
      (-< first
          rest)
      (-<< :_ (apply hash-map))
      ))
=> [{:1 (1 5)}]
```

In the same way, ```-<``` can accept a placeholder in its last position, signaling that it will get the input in the last position.

``` clojure
(->> 1
     (-< inc :_))
=> 2
```


## Installation

Add the snapshot library to your ```project.clj```

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

