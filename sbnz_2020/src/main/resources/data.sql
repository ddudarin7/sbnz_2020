
DELETE FROM `sbnz_2020`.`user_table` WHERE (`id` = '1');

INSERT INTO `sbnz_2020`.`user_table` (`class`, `id`, `first_name`, `last_name`, `password`, `role`, `username`) VALUES ('Admin', '1', 'admin', 'admin', 'admin', '1', 'admin');

DELETE FROM `sbnz_2020`.`medicine_ingredients`;
DELETE FROM `sbnz_2020`.`therapy_medicine`;
DELETE FROM `sbnz_2020`.`disease_therapies`;
DELETE FROM `sbnz_2020`.`disease_specific_symptoms`;
DELETE FROM `sbnz_2020`.`disease_non_specific_symptoms`;

DELETE FROM `sbnz_2020`.`ingredient`;
INSERT INTO `sbnz_2020`.`ingredient` (`id`, `name`)
VALUES 
	('1', 'S-adenosylmethionine'),
	('2', 'Silybin'),
	('3', 'Vitamin K1'),
	('4', 'Polysorbate 80'),
	('5', 'Propylene glycol'),
	('6', 'Sodium acetate anhydrous'),
	('7', 'Glacial acetic acid');


DELETE FROM `sbnz_2020`.`medicine`;
INSERT INTO `sbnz_2020`.`medicine` (`id`, `name`)
VALUES 
	('1', 'Denamarin'),
	('2', 'Vitamin K shot');
	

INSERT INTO `sbnz_2020`.`medicine_ingredients` (`medicine_id`, `ingredients_id`)
VALUES 
	('1', '1'),
	('1', '2'),
	('2', '3'),
	('2', '4'),
	('2', '5'),
	('2', '6'),
	('2', '7');
	
DELETE FROM `sbnz_2020`.`therapy`;
INSERT INTO `sbnz_2020`.`therapy` (`id`, `description`)
VALUES 
	('1', 'Change of diet. Denamarin prescription.'),
	('2', 'Vitamin K incresing'),
	('3', 'Chemotherapy'),
	('4', 'Radiotherapy'),
	('5', 'Cooling with wet blankets'),
	('6', 'Advanced hydratation'),
	('7', 'Decontamination'),
	('8', 'Supportive therapy'),
	('9', 'Polishing tooth under anesthesia'),
	('10', 'Tooth extraction'),
	('11', 'Psychotherapy');
	
INSERT INTO `sbnz_2020`.`therapy_medicine` (`therapy_id`, `medicine_id`)
VALUES 
	('1', '1'),
	('2', '2');
	
DELETE FROM `sbnz_2020`.`disease`;
INSERT INTO `sbnz_2020`.`disease` (`id`, `name`, `disease_group`)
VALUES 
	('1', 'Limfoma', 'CANCER'),
	('2', 'Plasmacytoma', 'CANCER'),
	('3', 'Leukemia', 'CANCER'),
	('4', 'Rat poisoning', 'POISONING'),
	('5', 'Chocolate poisoning', 'POISONING'),
	('6', 'Heat stroke', 'ENVIRONMENTAL'),
	('7', 'Tooth pathology', 'OTHER'),
	('8', 'Pica', 'BEHAVIORAL'),
	('9', 'Liver failure', 'OTHER'),
	('10', 'Insecticide poisoning', 'POISONING');
	
INSERT INTO `sbnz_2020`.`disease_therapies` (`disease_id`, `therapies_id`)
VALUES 
	('1', '3'),
	('2', '4'),
	('3', '3'),
	('4', '2'),
	('5', '8'),
	('6', '5'),
	('6', '6'),
	('7', '9'),
	('7', '10'),
	('8', '11'),
	('9', '1'),
	('10', '7');
	

INSERT INTO `sbnz_2020`.`disease_specific_symptoms` (`disease_id`, `specific_symptoms`)
VALUES 
	('1', 'NODES_ON_SKIN'),
	('2', 'NODES_ON_SKIN'),
	('3', 'PALE_GUMS'),
	('3', 'PALE_TONGUE'),
	('3', 'LOW_APPETITE'),
	('3', 'LOSS_OF_WEIGHT'),
	('4', 'HEAVY_BREATHING'),
	('4', 'COUGHING'),
	('4', 'BLOODY_GUMS'),
	('4', 'PALE_GUMS'),
	('4', 'FATIGUE'),
	('5', 'VOMITING'),
	('5', 'HYPERACTIVITY'),
	('6', 'HEAVY_BREATHING'),
	('6', 'UNCONSCIOUSNESS'),
	('7', 'BAD_BREATH'),
	('7', 'DENTAL_PLAQUE'),
	('7', 'GINGIVITIS'),
	('7', 'CHEWING_DISCOMFORT'),
	('8', 'NON_NUTRITIVE_FOOD_EATING'),
	('9', 'JAUNDICE'),
	('10', 'RUNNY_NOSE'),
	('10', 'VOMITING'),
	('10', 'DIARRHEA'),
	('10', 'FEVER'),
	('10', 'MUSCLE_CRAMP'),
	('10', 'DISORIENTATION');
	
	
INSERT INTO `sbnz_2020`.`disease_non_specific_symptoms` (`disease_id`, `non_specific_symptoms`)
VALUES 
	('1', 'LOSS_OF_WEIGHT'),
	('1', 'LOW_APPETITE'),
	('1', 'DEPRESSION'),
	('1', 'FEVER'),
	('1', 'FUR_LOSING'),
	('1', 'VOMITING'),
	('2', 'BONE_PAIN'),
	('2', 'BACK_PAIN'),
	('2', 'LAMENESS'),
	('2', 'RECTAL_BLEEDING'),
	('3', 'FATIGUE'),
	('3', 'FEVER'),
	('3', 'VOMITING'),
	('3', 'DEHYDRATION'),
	('3', 'HEAVY_BREATHING'),
	('3', 'BODY_BRUISES'),
	('3', 'DIARRHEA'),
	('3', 'LAMENESS'),
	('3', 'AGGRESSION'),
	('4', 'VOMITING'),
	('4', 'DIARRHEA'),
	('4', 'JOINT_PAIN'),
	('4', 'DARK_URINE'),
	('4', 'BODY_BRUISES'),
	('4', 'BLOODY_SNOUT'),
	('5', 'HYPERSALIVATION'),
	('5', 'DIARRHEA'),
	('5', 'SEIZURES'),
	('5', 'INCREASED_THIRST'),
	('5', 'FREQUENT_URINATION'),
	('5', 'HEAVY_BREATHING'),
	('5', 'MUSCLE_CRAMP'),
	('6', 'VOMITING'),
	('6', 'DIARRHEA'),
	('6', 'FEVER'),
	('7', 'MOUTH_BUMPS'),
	('7', 'TOOTH_LOST'),
	('8', 'VOMITING'),
	('8', 'DIARRHEA'),
	('8', 'PAINFUL_BLOATED_STOMACH'),
	('8', 'UCLERS'),
	('8', 'COUGHING'),
	('8', 'CHOKING'),
	('8', 'SNEEZING'),
	('9', 'VOMITING'),
	('9', 'LOW_APPETITE'),
	('9', 'LOSS_OF_WEIGHT'),
	('9', 'INCREASED_THIRST'),
	('10', 'LOSS_OF_WEIGHT'),
	('10', 'DEPRESSION'),
	('10', 'SEIZURES'),
	('10', 'INCREASED_HEART_RATE'),
	('10', 'HEAVY_BREATHING'),
	('10', 'TIGHTED_PUPILS');