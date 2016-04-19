# vending-machine-logic

The code seems to work in general but updating properties file messes up the order of the entries
 in that file due to underlying Hashtable used when setting properties entries.

Tests involving properties are pretty messy and brittle. I was not sure how to properly test file entries.