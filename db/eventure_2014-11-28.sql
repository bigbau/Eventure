----
-- phpLiteAdmin database dump (http://phpliteadmin.googlecode.com)
-- phpLiteAdmin version: 1.9.5
-- Exported: 7:04am on November 28, 2014 (CET)
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
-- Data dump for concepts, a total of 162 rows
----
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('place','event','0');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('sail','event','1');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('wear','event','2');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('name','event','3');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('make','event','4');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('see','event','5');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('surprise','event','6');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('sing','event','7');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('hear','event','8');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('lean','event','9');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('ring','event','10');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('introduce','event','11');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('color','event','12');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('go','event','13');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('startle','event','14');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('get','event','15');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('pick','event','16');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('say','event','17');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('long','state','18');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('placed','state','19');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('little','state','20');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('find','event','21');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('wondered','state','22');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('reached','state','23');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('rang','state','24');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('sang','state','25');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('escaped','state','26');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('introduced','state','27');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('slow','event','28');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('heard','state','29');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('named','state','30');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('wonder','event','31');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('reach','event','32');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('escape','event','33');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('move','event','34');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('fill','event','35');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('sting','event','36');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('smell','event','37');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('squeeze','event','38');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('climb','event','39');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('slide','event','40');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('pull','event','41');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('shout','event','42');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('cry','event','43');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('tug','event','44');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('leave','event','45');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('let','event','46');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('park','event','47');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('put','event','48');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('dip','event','49');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('stick','event','50');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('sniff','event','51');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('add','event','52');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('follow','event','53');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('roll','event','54');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('bump','event','55');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('tear','event','56');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('wrap','event','57');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('stare','event','58');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('close','event','59');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('sit','event','60');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('pulled','state','61');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('think','event','62');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('hid','state','63');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('favorite','state','64');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('cried','state','65');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('said','state','66');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('left','state','67');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('filled','state','68');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('watch','event','69');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('dreadful','state','70');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('jump','event','71');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('ran','state','72');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('stung','state','73');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('purple','state','74');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('terrifying','state','75');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('tail-whacker','state','76');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('was','state','77');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('race','event','78');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('ask','event','79');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('own','state','80');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('made','state','81');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('run','event','82');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('turned','state','83');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('bumped','state','84');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('found','state','85');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('present','state','86');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('leaned','state','87');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('wait','event','88');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('disappear','event','89');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('declare','event','90');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('live','event','91');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('hide','event','92');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('explore','event','93');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('morning','time','94');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('do','event','95');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('agree','event','96');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('know','event','97');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('serve','event','98');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('decide','event','99');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('enjoy','event','100');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('sound','event','101');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('prepare','event','102');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('done','state','103');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('asked','state','104');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('warn','event','105');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('could serve herself breakfast ','state','106');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('make it for Kay ','state','107');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('eat','event','108');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('look','event','109');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('start','event','110');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('seep','event','111');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('invite','event','112');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('please','event','113');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('be','event','114');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('gobble','event','115');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('starve','event','116');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('stop','event','117');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('take','event','118');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('thank','event','119');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('pile','event','120');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('grab','event','121');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('like','event','122');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('knock','event','123');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('walk','event','124');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('march','event','125');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('come','event','126');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('looked','state','127');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('prance','event','128');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('came','state','129');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('liked','state','130');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('let','state','131');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('proud','state','132');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('marched','state','133');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('piled','state','134');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('put','state','135');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('held','state','136');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('did','state','137');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('late-night','state','138');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('started','state','139');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('thought','state','140');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('stared','state','141');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('glared','state','142');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('slept','state','143');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('high','state','144');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('sleep','event','145');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('be marmalade','state','146');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('leap','event','147');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('forget','event','148');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('stay','event','149');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('fly','event','150');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('chase','event','151');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('spot','event','152');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('arise','event','153');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('hum','event','154');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('talk','event','155');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('surprising','state','156');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('yellow','state','157');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('hairy','state','158');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('leaped','state','159');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('forgotten','state','160');
INSERT INTO "concepts" ("concept","concept_type","conceptId") VALUES ('stayed','state','161');

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
-- Data dump for assertions, a total of 179 rows
----
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('0','1','EffectOf','0','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('2','1','EffectOf','1','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('3','4','EffectOf','2','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('5','6','EffectOf','3','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('7','8','EffectOf','4','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('9','10','EffectOf','5','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('11','12','EffectOf','6','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('13','14','EffectOf','7','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('15','16','EffectOf','8','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','18','EffectOfIsState','9','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('16','19','EffectOfIsState','10','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','20','EffectOfIsState','11','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('21','22','EffectOfIsState','12','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('1','23','EffectOfIsState','13','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('9','24','EffectOfIsState','14','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('8','25','EffectOfIsState','15','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','26','EffectOfIsState','16','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('12','27','EffectOfIsState','17','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('28','29','EffectOfIsState','18','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('1','30','EffectOfIsState','19','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('21','31','EventForGoalEvent','20','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','13','EventForGoalEvent','21','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','32','EventForGoalEvent','22','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('28','8','EventForGoalEvent','23','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','33','EventForGoalEvent','24','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('34','35','EffectOf','25','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('33','36','EffectOf','26','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('37','38','EffectOf','27','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('39','40','EffectOf','28','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('16','41','EffectOf','29','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('42','43','EffectOf','30','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('44','41','EffectOf','31','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('45','46','EffectOf','32','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('47','48','EffectOf','33','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('49','50','EffectOf','34','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('51','17','EffectOf','35','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','52','EffectOf','36','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('9','17','EffectOf','37','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('53','21','EffectOf','38','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('54','55','EffectOf','39','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('56','21','EffectOf','40','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('57','45','EffectOf','41','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('58','59','EffectOf','42','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('13','60','EffectOf','43','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('44','61','EffectOfIsState','44','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('62','63','EffectOfIsState','45','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('48','64','EffectOfIsState','46','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('50','65','EffectOfIsState','47','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('58','66','EffectOfIsState','48','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','67','EffectOfIsState','49','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('41','68','EffectOfIsState','50','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('69','70','EffectOfIsState','51','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('71','72','EffectOfIsState','52','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('33','73','EffectOfIsState','53','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('71','74','EffectOfIsState','54','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('35','75','EffectOfIsState','55','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','76','EffectOfIsState','56','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','77','EffectOfIsState','57','6');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('78','63','EffectOfIsState','58','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('79','77','EffectOfIsState','59','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','80','EffectOfIsState','60','6');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','81','EffectOfIsState','61','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('82','83','EffectOfIsState','62','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('54','84','EffectOfIsState','63','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('56','85','EffectOfIsState','64','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('57','86','EffectOfIsState','65','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','87','EffectOfIsState','66','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('53','85','EffectOfIsState','67','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('88','89','EventForGoalEvent','68','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('59','58','EventForGoalEvent','69','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('90','45','EventForGoalEvent','70','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('52','91','EventForGoalEvent','71','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('71','82','EventForGoalEvent','72','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('78','92','EventForGoalEvent','73','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('71','93','EventForGoalEvent','74','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('42','94','Happens','75','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('43','94','Happens','76','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('13','94','Happens','77','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('60','94','Happens','78','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('59','94','Happens','79','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('58','94','Happens','80','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','94','Happens','81','21');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('45','94','Happens','82','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('57','94','Happens','83','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('56','94','Happens','84','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('21','94','Happens','85','6');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('54','94','Happens','86','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('55','94','Happens','87','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('82','94','Happens','88','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('53','94','Happens','89','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('9','94','Happens','90','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('4','94','Happens','91','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('95','4','EffectOf','92','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('4','96','EffectOf','93','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('95','96','EffectOf','94','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('97','17','EffectOf','95','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('95','79','EffectOf','96','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('98','17','EffectOf','97','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('4','95','EffectOf','98','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('15','17','EffectOf','99','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('4','79','EffectOf','100','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('62','99','EffectOf','101','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('100','4','EffectOf','102','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('101','17','EffectOf','103','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('102','17','EffectOf','104','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('100','103','EffectOfIsState','105','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('95','66','EffectOfIsState','106','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('96','66','EffectOfIsState','107','3');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','104','EffectOfIsState','108','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','95','EventForGoalEvent','109','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('96','95','EventForGoalEvent','110','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('105','69','EventForGoalEvent','111','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('106','17','CauseOfIsState','112','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('107','79','CauseOfIsState','113','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('54','108','EffectOf','114','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('109','110','EffectOf','115','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('95','111','EffectOf','116','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('79','6','EffectOf','117','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('58','58','EffectOf','118','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('112','113','EffectOf','119','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('114','15','EffectOf','120','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('108','108','EffectOf','121','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('108','115','EffectOf','122','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('116','117','EffectOf','123','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('118','119','EffectOf','124','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('120','121','EffectOf','125','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('48','120','EffectOf','126','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('108','118','EffectOf','127','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('122','97','EffectOf','128','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('4','123','EffectOf','129','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('124','4','EffectOf','130','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('125','125','EffectOf','131','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('126','109','EffectOf','132','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('126','127','EffectOfIsState','133','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('128','129','EffectOfIsState','134','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('4','130','EffectOfIsState','135','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('123','131','EffectOfIsState','136','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('125','132','EffectOfIsState','137','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('124','133','EffectOfIsState','138','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('120','134','EffectOfIsState','139','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('118','66','EffectOfIsState','140','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('122','77','EffectOfIsState','141','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('118','135','EffectOfIsState','142','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('15','77','EffectOfIsState','143','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('6','136','EffectOfIsState','144','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('116','137','EffectOfIsState','145','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('108','138','EffectOfIsState','146','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('109','139','EffectOfIsState','147','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('17','140','EffectOfIsState','148','2');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('6','141','EffectOfIsState','149','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('58','142','EffectOfIsState','150','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('111','143','EffectOfIsState','151','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('109','144','EffectOfIsState','152','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('4','4','EventForGoalEvent','153','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('128','126','EventForGoalEvent','154','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('111','145','EventForGoalEvent','155','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('4','146','EventForGoalState','156','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('130','122','CauseOfIsState','157','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('82','147','EffectOf','158','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('46','17','EffectOf','159','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('96','148','EffectOf','160','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('13','149','EffectOf','161','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('95','149','EffectOf','162','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('150','95','EffectOf','163','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('151','152','EffectOf','164','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('153','13','EffectOf','165','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('122','60','EffectOf','166','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('154','155','EffectOf','167','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('13','77','EffectOfIsState','168','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('153','156','EffectOfIsState','169','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('152','157','EffectOfIsState','170','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('46','158','EffectOfIsState','171','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('82','159','EffectOfIsState','172','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('15','160','EffectOfIsState','173','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('13','161','EffectOfIsState','174','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('96','148','EventForGoalEvent','175','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('13','155','EventForGoalEvent','176','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('13','153','EventForGoalEvent','177','1');
INSERT INTO "assertions" ("concept1Id","concept2Id","relation","assertionId","frequency") VALUES ('126','15','EventForGoalEvent','178','1');

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
-- Data dump for concept_generalizations, a total of 436 rows
----
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('1','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','estimate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','estimate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('3','appoint');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','appoint');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','see');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('5','see');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','act');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('6','act');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','sing');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('7','sing');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('9','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('9','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('7','sound');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('10','sound');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('3','establish');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('11','establish');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('9','put');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('11','put');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('10','attach');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('11','attach');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('12','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('6','affect');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('12','affect');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('13','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('13','act');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('13','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('14','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('5','understand');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','understand');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('5','undergo');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','undergo');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('5','experience');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','experience');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('10','touch');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','touch');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('13','choose');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('16','choose');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('2','have');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('17','have');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','reach');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('21','reach');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','get');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('21','get');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('21','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('8','perceive');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('21','perceive');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('5','perceive');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('21','experience');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','get');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','change_state');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('13','change_state');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('32','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('32','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','communicate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('32','communicate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','leave');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('33','leave');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','cause');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('34','cause');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('34','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('34','act');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('34','affect');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('35','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('35','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('35','change_state');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('16','eat');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('35','eat');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','hurt');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('36','hurt');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('16','pierce');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('36','pierce');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('37','perceive');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('13','cause_to_be_perceived');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('37','cause_to_be_perceived');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('38','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('39','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('40','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('1','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('40','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('41','remove');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('16','remove');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('41','pull');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('16','pull');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('41','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('41','act');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','hit');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('41','hit');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('36','force');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('41','force');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('6','attack');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('42','attack');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('7','utter');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('42','utter');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('7','talk');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('42','talk');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('17','express');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('42','express');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('43','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('43','utter');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('43','express');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('44','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','attract');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('44','attract');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('44','pull');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('45','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('45','have');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('45','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','make');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('45','make');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','pass');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('45','pass');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','make');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('46','give');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('45','give');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('46','make');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('46','leave');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('47','put');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('48','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('48','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('48','estimate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','create_from_raw_material');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('49','create_from_raw_material');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('39','slope');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('49','slope');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('50','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('50','touch');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('50','attach');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('12','decorate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('50','decorate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('50','pierce');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('50','force');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('37','smell');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('51','smell');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('52','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('11','state');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('52','state');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('39','increase');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('52','increase');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('53','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('3','analyze');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('53','analyze');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('53','act');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('53','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('53','understand');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('53','choose');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('54','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('54','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('54','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('54','sound');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('21','pronounce');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('54','pronounce');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','delegate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('55','delegate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('55','hit');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('57','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','look');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('58','look');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('59','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('59','change_state');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('13','end');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('59','end');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('60','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('60','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('60','put');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','evaluate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('62','evaluate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('62','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('5','think');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('62','think');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('5','imagine');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('62','imagine');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('8','concentrate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('62','concentrate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','spend');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('48','spend');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','confuse');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('50','confuse');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('56','strip');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('41','strip');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('71','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('71','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('71','look');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('71','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('78','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('78','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('78','act');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('79','communicate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('17','request');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('79','request');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('48','give_voice');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('79','give_voice');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','race');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','race');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('2','last');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','last');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','direct');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','direct');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','become');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','become');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','pass');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('5','accompany');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','accompany');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','leave');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('32','succeed');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','succeed');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('54','function');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','function');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('59','trade');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','trade');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('69','check');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('53','check');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('88','act');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('35','work');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('88','work');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('89','end');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('28','weaken');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('89','weaken');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('90','evaluate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('90','state');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('53','play');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('90','play');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('91','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('91','experience');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('78','compete');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','compete');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('92','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','score');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','score');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('32','achieve');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','achieve');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('95','spend');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('95','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('95','make');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','carry_through');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('95','carry_through');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('95','create');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','create');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('46','accept');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('96','accept');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('97','experience');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('97','accept');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('98','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('98','spend');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','effect');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('98','effect');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('98','work');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('35','provide');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('98','provide');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('98','function');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('97','copulate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('98','copulate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('98','satisfy');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('95','satisfy');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','determine');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('99','determine');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('99','end');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('46','induce');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('99','induce');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('100','experience');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('48','use');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('100','use');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('101','look');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('101','pronounce');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('101','cause_to_be_perceived');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('43','announce');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('101','announce');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('102','sound');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('11','initiate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('102','initiate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('102','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('102','make');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('21','learn');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('102','learn');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('102','create_from_raw_material');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('5','watch');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('69','watch');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','order');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('105','order');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('11','inform');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('105','inform');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('108','spend');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('108','eat');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('35','consume');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('108','consume');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('109','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('9','trust');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('109','trust');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('17','convey');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('109','convey');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('96','match');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('109','match');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('110','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('110','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('110','act');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('110','make');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('11','begin');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('110','begin');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('110','leave');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('110','play');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('54','change_shape');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('110','change_shape');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('82','run');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('111','run');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('112','request');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('113','satisfy');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('114','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('115','eat');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('115','utter');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('117','end');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','catch');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('117','catch');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','have');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','undergo');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','experience');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('5','receive');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','receive');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','act');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('7','interpret');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','interpret');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','get');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('44','transport');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','transport');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','accept');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','use');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','become');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','work');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('90','affirm');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','affirm');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('119','convey');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('120','put');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('48','arrange');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('120','arrange');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('121','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','seize');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('121','seize');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('114','take');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('121','take');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('4','head');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','head');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','buy');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','buy');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('15','sicken');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','sicken');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('122','see');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('116','desire');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('122','desire');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('123','sound');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('123','hit');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('16','strike');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('123','strike');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('124','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('124','score');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('124','accompany');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('38','compel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('124','compel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('124','play');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('118','traverse');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('124','traverse');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('125','touch');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('0','rank');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','rank');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','become');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','experience');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('13','happen');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','happen');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('32','arrive');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','arrive');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','run');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('5','care');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('109','care');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','result');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('53','result');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','originate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('53','originate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('126','proceed');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('95','proceed');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('128','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('125','walk');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('128','walk');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('147','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('71','neglect');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('148','neglect');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('149','fit');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('13','fit');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('149','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('149','check');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('114','stay');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('149','stay');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('50','stay_in_place');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('149','stay_in_place');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('50','fasten');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('149','fasten');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('150','move');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('150','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('150','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('150','hit');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('28','decrease');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('150','decrease');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('150','transport');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('152','change');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('21','spy');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('152','spy');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('153','travel');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('153','become');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('153','happen');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('60','change_posture');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('153','change_posture');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('125','protest');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('153','protest');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('154','sing');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('154','be');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('154','sound');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('155','communicate');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('45','tell');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('155','tell');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('102','teach');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('155','teach');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('45','lose');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('148','lose');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('7','unwrap');
INSERT INTO "concept_generalizations" ("conceptId","generalization") VALUES ('155','unwrap');

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
-- Data dump for metadata, a total of 87 rows
----
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('hours','object','1','0','0','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('pajamas','object','2','1','1','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('Miss','object','3','2','2','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES (' Mandy','object','3','2','3','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('ship','object','5','3','4','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES (' sail','object','5','3','5','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('song','object','7','4','6','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES (' speck','object','7','4','7','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('speck','object','8','4','8','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('sailor','object','15','8','9','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('boat','object','16','8','10','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('boat','object','16','10','11','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('hours','object','1','13','12','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('speck','object','8','15','13','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('boat','object','28','18','14','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('miles','object','31','20','15','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('destination','object','32','22','16','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('boat','object','28','23','17','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('squeak','object','8','23','18','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('room','object','35','25','19','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('tail','object','36','26','20','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('something','object','37','27','21','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('blanket','object','39','28','22','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('hill','object','40','28','23','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('herbs','object','16','29','24','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES (' petals','object','16','29','25','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('bag','object','41','29','26','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('humans','object','45','32','27','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('Cheesepuff','object','46','32','28','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES (' Whiskers','object','46','32','29','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('traps','object','48','33','30','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('paw','object','49','34','31','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('air','object','51','35','32','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('carrot','object','21','38','33','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('bowl','object','55','39','34','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('paper','object','56','40','35','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('construction','object','21','40','36','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('paper','object','57','41','37','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('traps','object','48','46','38','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('bag','object','41','50','39','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('Whiskers','object','69','51','40','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('room','object','35','55','41','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('paper','object','56','64','42','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('paper','object','57','65','43','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('days','object','88','68','44','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('paper','object','57','83','45','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('paper','object','56','84','46','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('construction','object','21','85','47','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('bowl','object','55','87','48','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('carrot','object','21','85','49','3');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('bed','object','4','92','50','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('things','object','95','96','51','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('breakfast','object','98','97','52','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('bed','object','4','98','53','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('Kay','object','4','100','54','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES (' bed','object','4','100','55','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('bed','object','4','102','56','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('breakfast','object','102','104','57','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('things','object','95','106','58','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('things','object','95','110','59','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('bed','object','105','111','60','2');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('Billy','object','54','114','61','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES (' way','object','54','114','62','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('Billy','object','6','117','63','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('strawberry','object','108','121','64','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('pizzas','object','108','121','65','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('bite','object','118','124','66','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('bread','object','120','125','67','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('cup','object','121','125','68','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('plate','object','118','127','69','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('marmalade','object','122','128','70','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('Billy','object','97','128','71','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('marmalade','object','4','129','72','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('street','object','125','131','73','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('bite','object','118','140','74','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('marmalade','object','122','141','75','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('plate','object','118','142','76','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('Billy','object','6','144','77','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('Billy','object','6','149','78','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('marmalade','object','4','153','79','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('Scat','object','46','159','80','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('night','object','149','161','81','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('stick','object','151','164','82','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('dogs','object','122','166','83','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('day','object','155','167','84','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('Scat','object','46','171','85','1');
INSERT INTO "metadata" ("metadatum","metadata_type","conceptId","assertionId","metadatumId","frequency") VALUES ('home','object','15','173','86','1');

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
-- Data dump for metadata_generalizations, a total of 8 rows
----
INSERT INTO "metadata_generalizations" ("metadatumId","generalization") VALUES ('4','artifact');
INSERT INTO "metadata_generalizations" ("metadatumId","generalization") VALUES ('5','artifact');
INSERT INTO "metadata_generalizations" ("metadatumId","generalization") VALUES ('47','act');
INSERT INTO "metadata_generalizations" ("metadatumId","generalization") VALUES ('49','act');
INSERT INTO "metadata_generalizations" ("metadatumId","generalization") VALUES ('61','artifact');
INSERT INTO "metadata_generalizations" ("metadatumId","generalization") VALUES ('62','artifact');
INSERT INTO "metadata_generalizations" ("metadatumId","generalization") VALUES ('64','food');
INSERT INTO "metadata_generalizations" ("metadatumId","generalization") VALUES ('65','food');

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
-- Data dump for metadata_synonyms, a total of 0 rows
----

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
-- Data dump for concept_synonyms, a total of 100 rows
----
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('4','3');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('3','4');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('5','8');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('8','5');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('4','15');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('15','4');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('13','15');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('15','13');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('5','21');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('21','5');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('15','21');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('21','15');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('4','32');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('32','4');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('13','34');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('34','13');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('43','42');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('42','43');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('15','46');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('46','15');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('0','48');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('48','0');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('15','50');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('50','15');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('36','50');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('50','36');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('21','55');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('55','21');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('41','56');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('56','41');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('54','57');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('57','54');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('5','69');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('69','5');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('53','69');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('69','53');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('14','71');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('71','14');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('9','82');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('82','9');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('13','82');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('82','13');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('33','82');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('82','33');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('34','82');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('82','34');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('78','82');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('82','78');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('13','91');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('91','13');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('4','95');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('95','4');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('91','97');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('97','91');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('95','98');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('98','95');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('13','101');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('101','13');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('4','102');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('102','4');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('5','109');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('109','5');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('88','109');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('109','88');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('13','110');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('110','13');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('14','110');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('110','14');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('15','110');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('110','15');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('71','110');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('110','71');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('53','114');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('114','53');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('91','114');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('114','91');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('4','118');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('118','4');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('15','118');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('118','15');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('35','118');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('118','35');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('79','118');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('118','79');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('55','123');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('123','55');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('15','126');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('126','15');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('53','126');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('126','53');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('95','126');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('126','95');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('71','147');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('147','71');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('45','148');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('148','45');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('50','149');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('149','50');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('7','155');
INSERT INTO "concept_synonyms" ("concept1Id","concept2Id") VALUES ('155','7');

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

----
-- structure for index sqlite_autoindex_concept_synonyms_1 on table concept_synonyms
----
;
COMMIT;
