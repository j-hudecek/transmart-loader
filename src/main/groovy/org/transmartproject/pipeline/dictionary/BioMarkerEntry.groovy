package org.transmartproject.pipeline.dictionary

/** Serves as a data container for an entry that is to be added to a
 *  dictionary. Use it with DictionaryLoader to insert the symbol and synonyms
 *  into the appropriate database tables.
 */
class BioMarkerEntry {

    String symbol
    String description
    String organism
    String source
    String externalID
    String markerType
    String displayCategory
    List<String> synonyms = new ArrayList<String>()

    public BioMarkerEntry(String markerType, String displayCategory) {
        this.markerType = markerType
        this.displayCategory = displayCategory
    }

}