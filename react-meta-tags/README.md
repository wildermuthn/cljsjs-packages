# cljsjs/react-meta-tags

[](dependency)
```clojure
[cljsjs/react-meta-tags "0.3.0-1"] ;; latest release
```
[](/dependency)

This jar comes with `deps.cljs` as used by the [Foreign Libs][flibs] feature
of the ClojureScript compiler. After adding the above dependency to your project
you can require the packaged library like so:

```clojure
(ns application.core
  (:require [cljsjs.react-meta-tags]))

(def meta-tags (r/adapt-react-class (aget js/MetaTags "default")))
```

[flibs]: https://github.com/clojure/clojurescript/wiki/Packaging-Foreign-Dependencies
