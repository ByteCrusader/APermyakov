package ru.apermyakov.nonblock;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Class for modulate non block cache.
 *
 * @author apermyakov
 * @version 1.0
 * @since 21.11.2017
 * @param <K> key type
 * @param <V> value type
 */
public class NonBlockCache<K, V> {

    /**
     * Class for modulate model with field for version.
     *
     * @author apermyakov
     * @version 1.0
     * @since 22.11.2017
     * @param <V> value type
     */
    public static class Model<V> {

        /**
         * Field for model's version.
         */
        private double version = 1.0;

        /**
         * Field for model's value
         */
        private V value;

        /**
         * Design model.
         *
         * @param value value
         */
        Model(V value) {
            this.value = value;
        }

        /**
         * Method for update value and increase version.
         *
         * @param value value
         * @return result model
         */
        protected Model<V> updateValue(V value) {
            this.value = value;
            version += 0.1D;
            return this;
        }

        /**
         * Version getter.
         *
         * @return version
         */
        public double getVersion() {
            return this.version;
        }

        /**
         * Value getter.
         *
         * @return value
         */
        public V getValue() {
            return value;
        }
    }

    /**
     * Field for concurrency cache.
     */
    private ConcurrentHashMap<K, Model<V>> cache = new ConcurrentHashMap<>();

    /**
     * Method for concurrency add to cache.
     *
     * @param key key
     * @param value value
     */
    public void add(K key, Model<V> value) {
        cache.put(key, value);
    }

    /**
     * Method for check read version and current version of model.
     *
     * @param v current model
     * @param readVersion read version
     */
    private void checkVersion(Model<V> v, double readVersion) {
        if (readVersion != v.getVersion()) {
            try {
                throw new OplimisticException("Race condition, data was erased!");
            } catch (OplimisticException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method for update model.
     *
     * @param v current model
     * @param readVersion read version of model
     * @param value new value
     * @return new model
     */
    private Model<V> updateModel(Model<V> v, double readVersion, V value) {
        checkVersion(v, readVersion);
        return v.updateValue(value);
    }

    /**
     * Method for update model if exist.
     *
     * @param key key
     * @param value new value
     */
    public void update(K key, V value) throws OplimisticException {
        if (cache.containsKey(key)) {
            double versionWhenRead = cache.get(key).getVersion();
            cache.computeIfPresent(key, (k, v) -> updateModel(v, versionWhenRead, value));
        } else {
            throw new OplimisticException("Model has already deleted by another thread or has not been added");
        }
    }

    /**
     * Method for delete model by key.
     *
     * @param key key
     */
    public void delete(K key) {
        cache.remove(key);
    }

    /**
     * Own class of oplimistic exception.
     *
     * @author apermyakov
     * @version 1.0
     * @since 21.11.2017
     */
    private static class OplimisticException extends Throwable {

        /**
         * Design exception.
         *
         * @param string text
         */
        private OplimisticException(String string) {
            super(string);
        }
    }

    /**
     * Main method.
     *
     * @param args args
     */
    public static void main(String[] args) {
        NonBlockCache<Integer, String> cache = new NonBlockCache<>();

        cache.add(1, new Model<>("First"));
        cache.add(2, new Model<>("Second"));

        Thread firstUpdater = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cache.update(1, "Third");
                } catch (OplimisticException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread secondUpdater = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cache.update(1, "Fourth");
                } catch (OplimisticException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thirdUpdater = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    cache.update(3, "Fourth");
                } catch (OplimisticException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread firstAdder = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.add(3, new Model<>("Third"));
            }
        });

        Thread secondAdder = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.add(4, new Model<>("Fourth"));
            }
        });

        Thread firstDeleter = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.delete(3);
            }
        });

        Thread secondDeleter = new Thread(new Runnable() {
            @Override
            public void run() {
                cache.delete(4);
            }
        });

        firstAdder.start();
        secondAdder.start();
        firstUpdater.start();
        secondUpdater.start();
        thirdUpdater.start();
        firstDeleter.start();
        secondDeleter.start();
    }
}
