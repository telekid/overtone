(ns overtone.live
  (:refer-clojure :exclude (rand + * - / bit-not mod))
  (:require midi osc byte-spec
            clojure.stacktrace
            (overtone.core config time-utils log sc ugen synth synthdef envelope sample)
            (overtone.music rhythm pitch tuning)
            (overtone.gui curve scope)
            (overtone.studio fx)))

; TODO: make this work with namespace prefixes too...
;   (immigrate 'overtone.instruments)
(defn immigrate
 "Create a public var in this namespace for each public var in the
 namespaces named by ns-names. The created vars have the same name, value
 and metadata as the original except that their :ns metadata value is this
 namespace."
 [& ns-names]
 (doseq [ns ns-names]
   (doseq [[sym var] (ns-publics ns)]
     (let [sym (with-meta sym (assoc (meta var) :ns *ns*))]
       (if (.isBound var)
         (intern *ns* sym (var-get var))
         (intern *ns* sym))))))

(immigrate
  'osc
  'midi
  'overtone.core.time-utils
  'overtone.core.util
  'overtone.core.event
  'overtone.core.sc
  'overtone.core.ugen
  'overtone.core.synth
  'overtone.core.sample
  'overtone.core.synthdef
  'overtone.core.envelope
  'overtone.music.rhythm
  'overtone.music.pitch
  'overtone.music.tuning
  'overtone.gui.curve
  'overtone.gui.scope
  'overtone.studio.fx
  'overtone.ugens
  'clojure.stacktrace
  )
