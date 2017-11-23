(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[cljsjs/boot-cljsjs "0.9.0" :scope "test"]
                  [cljsjs/react "15.6.1-1"]
                  [cljsjs/react-dom "15.6.1-1"]])

(require '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +lib-version+ "1.11.2")
(def +version+ (str +lib-version+ "-0"))

(task-options!
  push {:ensure-clean false}
  pom {:project     'cljsjs/react-joyride
       :version     +version+
       :description "Create walkthroughs and guided tours for your apps"
       :url         "https://github.com/gilbarbara/react-joyride"
       :license     {"MIT" "https://raw.githubusercontent.com/gilbarbara/react-joyride/master/LICENSE"}
       :scm         {:url "https://github.com/cljsjs/packages"}})

(deftask package []
         (comp

           (download :url (str "https://cdn.rawgit.com/wildermuthn/react-joyride/" +lib-version+ "/dist/bundle.js")
                     :checksum "a7e9d19343e663e42dfbe84e74c28bc7"
                     :unzip false)

           (download :url (str "https://cdn.rawgit.com/wildermuthn/react-joyride/" +lib-version+  "/dist/react-joyride-compiled.css")
                     :checksum "b9e3722d56bf3b6ffd17f4d4ff504608"
                     :unzip false)

           (sift :move {
                        #"^bundle.js$"                   "cljsjs/react-joyride/development/react-joyride.inc.js"
                        #"^bundle.js$"                   "cljsjs/react-joyride/production/react-joyride.min.inc.js"
                        #"^react-joyride-compiled.css$" "cljsjs/react-joyride/development/react-joyride.inc.css"
                        #"^react-joyride-compiled.css$" "cljsjs/react-joyride/production/react-joyride.min.inc.css"})

           (sift :include #{#"cljsjs"})

           (deps-cljs :name "cljsjs.react-joyride"
                      :requires ["cljsjs.react"
                                 "cljsjs.react.dom"])

           (pom)

           (jar)))