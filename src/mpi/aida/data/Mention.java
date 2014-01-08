package mpi.aida.data;

import java.io.Serializable;

public class Mention implements Serializable, Comparable<Mention> {

  private static final long serialVersionUID = 3177945435296705498L;

  /** 
   * This holds the mention string exactly 
   * as found in the text. */
  private String mention;
  
  /** 
   * This holds the normalized mention, e.g. after 
   * lemmatizing/stemming the original mention.
   * Use this for candidate lookup in the disambiguation
   * phase.
   */
  private String normalizedMention;
  
  /**
   * Needed by CoNLL reader.
   */
  private String ner;

  /** Starting token offset of the mention. */
  private int startToken;

  /** Ending token offset of the mention (including this token). */
  private int endToken;

  private int startStanford;

  private int endStanford;

  private int sentenceId;

  private String groundTruthEntity = null;

  private double disambiguationConfidence;

  // Character offset
  private int charOffset, charLength;

  private Entities candidateEntities;

  private int id = -1;
    
  /**
   * Occurrence count either in the collection or in a document. Set as needed.
   */
  private int occurrenceCount = 0;

  public Mention() {
  }

  public Mention(String mention, int startToken, int endToken, int startStanford, int endStanford, int sentenceId) {
    this.startToken = startToken;
    this.endToken = endToken;
    this.startStanford = startStanford;
    this.endStanford = endStanford;
    this.mention = mention;
    this.sentenceId = sentenceId;
  }

  public String getMention() {
    return mention;
  }

  public int getStartToken() {
    return startToken;
  }

  public int getEndToken() {
    return endToken;
  }

  public int getStartStanford() {
    return startStanford;
  }

  public int getEndStanford() {
    return endStanford;
  }

  public int getSentenceId() {
    return sentenceId;
  }

  public void setSentenceId(int sentenceId) {
    this.sentenceId = sentenceId;
  }

  public void addCandidateEntity(Entity entity) {
    candidateEntities.add(entity);
  }

  public Entities getCandidateEntities() {
    return candidateEntities;
  }

  public void setCandidateEntities(Entities candidateEntities) {
    this.candidateEntities = candidateEntities;
  }

  public String toString() {
    String norm = (normalizedMention != null) ? ("[" + normalizedMention + "]") : "";
    return mention + norm + ", From:" + startToken + "/" + startStanford + ", To:" + endToken + "/" + endStanford + ", Offset: " + charOffset + ", Length: " + charLength;
  }

  public void setStartToken(int start) {
    this.startToken = start;
  }

  public void setEndToken(int end) {
    this.endToken = end;
  }

  public int getCharOffset() {
    return this.charOffset;
  }

  public int getCharLength() {
    return this.charLength;
  }

  public void setCharOffset(int offset) {
    this.charOffset = offset;

  }

  public void setCharLength(int length) {
    this.charLength = length;
  }

  public void setMention(String mention) {
    this.mention = mention;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    } else {
      if (obj instanceof Mention) {
        Mention m = (Mention) obj;
  
        return m.getMention().equals(getMention()) && 
               m.getCharOffset() == charOffset;
      } else {
        return false;
      }
    }
  }

  @Override
  public int hashCode() {
    return mention.hashCode() + charOffset;
  }

  @Override
  public int compareTo(Mention mention) {
    return this.charOffset - mention.charOffset;
  }

  public void setGroundTruthResult(String result) {
    this.groundTruthEntity = result;
  }

  public String getGroundTruthResult() {
    return groundTruthEntity;
  }

  public void setDisambiguationConfidence(double confidence) {
    disambiguationConfidence = confidence;
  }

  public double getDisambiguationConfidence() {
    return disambiguationConfidence;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setStartStanford(int startStanford) {
    this.startStanford = startStanford;
  }

  public void setEndStanford(int endStanford) {
    this.endStanford = endStanford;
  }

  public String getIdentifiedRepresentation() {
    return mention + ":::" + charOffset;
  }

  public int getOccurrenceCount() {
    return occurrenceCount;
  }

  public void setOccurrenceCount(int occurrenceCount) {
    this.occurrenceCount = occurrenceCount;
  }

  public void setNormalizedMention(String normalizedMention) {
    this.normalizedMention = normalizedMention;
  }

  public String getNormalizedMention() {
    String m = normalizedMention;
    if (m == null) {
      m = mention;
    }
    return m;
  }

  /**
   * @param ner the ner to set
   */
  public void setNer(String ner) {
    this.ner = ner;
  }

  /**
   * @return the ner
   */
  public String getNer() {
    return ner;
  }
}
