----
-- phpLiteAdmin database dump (http://phpliteadmin.googlecode.com)
-- phpLiteAdmin version: 1.9.5
-- Exported: 6:33pm on November 25, 2014 (CET)
-- database file: C:\Users\RJ\Desktop\Thesis\Eventure\eventure.db
----
BEGIN TRANSACTION;

----
-- Table structure for concept_types
----
CREATE TABLE concept_types (
  concept_type TEXT PRIMARY KEY NOT NULL
);

----
-- Data dump for concept_types, a total of 3 rows
----
INSERT INTO "concept_types" ("concept_type") VALUES ('event');
INSERT INTO "concept_types" ("concept_type") VALUES ('state');
INSERT INTO "concept_types" ("concept_type") VALUES ('time');

----
-- Table structure for metadata_types
----
CREATE TABLE metadata_types (
  metadata_type TEXT NOT NULL PRIMARY KEY
);

----
-- Data dump for metadata_types, a total of 2 rows
----
INSERT INTO "metadata_types" ("metadata_type") VALUES ('adverb');
INSERT INTO "metadata_types" ("metadata_type") VALUES ('object');

----
-- Table structure for relations
----
CREATE TABLE relations (
  relation TEXT NOT NULL PRIMARY KEY
);

----
-- Data dump for relations, a total of 6 rows
----
INSERT INTO "relations" ("relation") VALUES ('CauseOfIsState');
INSERT INTO "relations" ("relation") VALUES ('EffectOf');
INSERT INTO "relations" ("relation") VALUES ('EffectOfIsState');
INSERT INTO "relations" ("relation") VALUES ('EventForGoalEvent');
INSERT INTO "relations" ("relation") VALUES ('EventForGoalState');
INSERT INTO "relations" ("relation") VALUES ('Happens');

----
-- Table structure for concepts
----
CREATE TABLE concepts (
  concept TEXT NOT NULL,
  concept_type TEXT NOT NULL,
  conceptId INTEGER DEFAULT 0 NOT NULL UNIQUE,
  PRIMARY KEY(concept,concept_type),
  FOREIGN KEY (concept_type) REFERENCES concept_types (concept_type) ON DELETE CASCADE ON UPDATE CASCADE
);

----
-- Data dump for concepts, a total of 2 rows
----
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('give','event','0');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('smile','event','1');

----
-- Table structure for assertions
----
CREATE TABLE assertions (
  concept1Id INTEGER NOT NULL,
  concept2Id INTEGER NOT NULL,
  relation TEXT NOT NULL,
  assertionId INTEGER DEFAULT 0 NOT NULL UNIQUE,
  frequency INTEGER NOT NULL DEFAULT 1,
  PRIMARY KEY (concept1Id,concept2Id,relation),
  FOREIGN KEY (relation) REFERENCES relations (relation) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (concept1Id) REFERENCES concepts (conceptId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (concept2Id) REFERENCES concepts (conceptId) ON DELETE CASCADE ON UPDATE CASCADE
);

----
-- Data dump for assertions, a total of 1 rows
----
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('0','1','EffectOf','0','6');

----
-- Table structure for concept_generalizations
----
CREATE TABLE concept_generalizations (
  conceptId INTEGER NOT NULL,
  generalization TEXT NOT NULL,
  PRIMARY KEY (conceptId,generalization),
  FOREIGN KEY (conceptId) REFERENCES concepts (conceptId) ON DELETE CASCADE ON UPDATE CASCADE
);

----
-- Data dump for concept_generalizations, a total of 6 rows
----
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('1','communicate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','communicate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('1','convey');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','convey');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('1','express');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','express');

----
-- Table structure for concept_synonyms
----
CREATE TABLE concept_synonyms (
  concept1Id INTEGER NOT NULL,
  concept2Id INTEGER NOT NULL,
  PRIMARY KEY (concept1Id,concept2Id),
  FOREIGN KEY (concept1Id) REFERENCES concepts (conceptId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (concept2Id) REFERENCES concepts (conceptId) ON DELETE CASCADE ON UPDATE CASCADE
);

----
-- Data dump for concept_synonyms, a total of 0 rows
----

----
-- Table structure for metadata
----
CREATE TABLE metadata (
  metadatum TEXT NOT NULL,
  metadata_type TEXT NOT NULL,
  conceptId INTEGER NOT NULL,
  assertionId INTEGER NOT NULL,
  metadatumId INTEGER DEFAULT 0 NOT NULL UNIQUE,
  frequency INTEGER NOT NULL DEFAULT 1,
  PRIMARY KEY (metadatum,metadata_type,conceptId,assertionId),
  FOREIGN KEY (metadata_type) REFERENCES metadata_types (metadata_type) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (conceptId) REFERENCES concepts (conceptId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (assertionId) REFERENCES assertions (assertionId) ON DELETE CASCADE ON UPDATE CASCADE
);

----
-- Data dump for metadata, a total of 5 rows
----
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('gladly','adverb','0','0','0','6');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('willingly','adverb','0','0','1','4');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('happily','adverb','1','0','2','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('cheerfully','adverb','1','0','3','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('merrily','adverb','1','0','4','2');

----
-- Table structure for metadata_generalizations
----
CREATE TABLE metadata_generalizations (
  metadatumId INTEGER NOT NULL,
  generalization TEXT NOT NULL,
  PRIMARY KEY (metadatumId,generalization),
  FOREIGN KEY (metadatumId) REFERENCES metadata (metadatumId) ON DELETE CASCADE ON UPDATE CASCADE
);

----
-- Data dump for metadata_generalizations, a total of 0 rows
----

----
-- Table structure for metadata_synonyms
----
CREATE TABLE metadata_synonyms (
  metadatum1 INTEGER NOT NULL,
  metadatum2 INTEGER NOT NULL,
  PRIMARY KEY (metadatum1,metadatum2),
  FOREIGN KEY (metadatum1) REFERENCES metadata (metadatumId) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (metadatum2) REFERENCES metadata (metadatumId) ON DELETE CASCADE ON UPDATE CASCADE
);

----
-- Data dump for metadata_synonyms, a total of 2 rows
----
INSERT INTO "metadata_synonyms" ("metadatum1","metadatum2") VALUES ('2','4');
INSERT INTO "metadata_synonyms" ("metadatum1","metadatum2") VALUES ('4','2');

----
-- structure for index sqlite_autoindex_concept_types_1 on table concept_types
----
;

----
-- structure for index sqlite_autoindex_metadata_types_1 on table metadata_types
----
;

----
-- structure for index sqlite_autoindex_relations_1 on table relations
----
;

----
-- structure for index sqlite_autoindex_concepts_1 on table concepts
----
;

----
-- structure for index sqlite_autoindex_concepts_2 on table concepts
----
;

----
-- structure for index sqlite_autoindex_assertions_1 on table assertions
----
;

----
-- structure for index sqlite_autoindex_assertions_2 on table assertions
----
;

----
-- structure for index sqlite_autoindex_concept_generalizations_1 on table concept_generalizations
----
;

----
-- structure for index sqlite_autoindex_concept_synonyms_1 on table concept_synonyms
----
;

----
-- structure for index sqlite_autoindex_metadata_1 on table metadata
----
;

----
-- structure for index sqlite_autoindex_metadata_2 on table metadata
----
;

----
-- structure for index sqlite_autoindex_metadata_generalizations_1 on table metadata_generalizations
----
;

----
-- structure for index sqlite_autoindex_metadata_synonyms_1 on table metadata_synonyms
----
;
COMMIT;
