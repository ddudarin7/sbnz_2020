DELETE FROM `sbnz_2020`.`diagnose_therapies`;
DELETE FROM `sbnz_2020`.`diagnose_specific_symptoms_matched`;
DELETE FROM `sbnz_2020`.`diagnose_non_specific_symptoms_matched`;
DELETE FROM `sbnz_2020`.`diagnose`;

DELETE FROM `sbnz_2020`.`user_table` WHERE (`id` = '1');
DELETE FROM `sbnz_2020`.`user_table` WHERE (`id` = '2');

INSERT INTO `sbnz_2020`.`user_table` (`class`, `id`, `first_name`, `last_name`, `password`, `role`, `username`) VALUES ('Admin', '1', 'admin', 'admin', 'admin', '1', 'admin');
INSERT INTO `sbnz_2020`.`user_table` (`class`, `id`, `first_name`, `last_name`, `password`, `role`, `username`) VALUES ('Vet', '2', 'vet', 'vet', 'vet', '0', 'vet');


DELETE FROM `sbnz_2020`.`patient_vaccinations`;
DELETE FROM `sbnz_2020`.`vaccination`;
DELETE FROM `sbnz_2020`.`patient_ingredient_allergies`;
DELETE FROM `sbnz_2020`.`patient_medicine_allergies`;
DELETE FROM `sbnz_2020`.`medicine_ingredients`;
DELETE FROM `sbnz_2020`.`therapy_medicine`;
DELETE FROM `sbnz_2020`.`disease_therapies`;
DELETE FROM `sbnz_2020`.`disease_specific_symptoms`;
DELETE FROM `sbnz_2020`.`disease_non_specific_symptoms`;


DELETE FROM `sbnz_2020`.`symptom`;
INSERT INTO `sbnz_2020`.`symptom` (`id`, `name`)
VALUES 
	('1', 'VOMITING'),
    ('2', 'DIARRHEA'),
    ('3', 'INCREASED_THIRST'),
    ('4', 'INCREASED_HEART_RATE'),
    ('5', 'APATHY'),
    ('6', 'DEPRESSION'),
    ('7', 'SCRATCHING'),
    ('8', 'BROWN_DOTS_ON_BODY'),
    ('9', 'DARK_URINE'),
    ('10', 'AGGRESSION'),
    ('11', 'COUGHING'),
    ('12', 'FEVER'),
    ('13', 'LOW_APPETITE'),
    ('14', 'FATIGUE'),
    ('15', 'JOINT_PAIN'),
    ('16', 'HEAVY_BREATHING'),
    ('17', 'KIDNEY_FAILURE'),
    ('18', 'CRACKED_SKIN'),
    ('19', 'DRY_SKIN'),
    ('20', 'RED_DOTS_ON_SKIN'),
    ('21', 'NERVOUSNESS'),
    ('22', 'UNCONSCIOUSNESS'),
    ('23', 'LOSS_OF_WEIGHT'),
    ('24', 'MUSCLE_CRAMP'),
    ('25', 'SKIN_FLAKING'),
    ('26', 'INFLAMMATION_OF_SKIN'),
    ('27', 'YELLOW_MUCOSA'),
    ('28', 'RECTAL_BLEEDING'),
    ('29', 'CONJUCTTIVITIS'),
    ('30', 'HYPERSALIVATION'),
    ('31', 'PAINFUL_BLOATED_STOMACH'),
    ('32', 'FLUID_IN_THE_STOMACH'),
    ('33', 'CROOKED_FRONT_LEGS'),
    ('34', 'RIB_OSTEOPHYTES'),
    ('35', 'NODES_ON_SKIN'),
    ('36', 'PALE_GUMS'),
    ('37', 'PALE_TONGUE'),
    ('38', 'BLOODY_GUMS'),
    ('39', 'HYPERACTIVITY'),
    ('40', 'BAD_BREATH'),
    ('41', 'DENTAL_PLAQUE'),
    ('42', 'GINGIVITIS'),
    ('43', 'CHEWING_DISCOMFORT'),
    ('44', 'NON_NUTRITIVE_FOOD_EATING'),
    ('45', 'JAUNDICE'),
    ('46', 'RUNNY_NOSE'),
    ('47', 'DISORIENTATION'),
    ('48', 'FUR_LOSING'),
    ('49', 'BONE_PAIN'),
    ('50', 'BACK_PAIN'),
    ('51', 'LAMENESS'),
    ('52', 'DEHYDRATION'),
    ('53', 'BODY_BRUISES'),
    ('54', 'BLOODY_SNOUT'),
    ('55', 'SEIZURES'),
    ('56', 'FREQUENT_URINATION'),
    ('57', 'TOOTH_LOST'),
    ('58', 'MOUTH_BUMPS'),
    ('59', 'UCLERS'),
    ('60', 'CHOKING'),
    ('61', 'SNEEZING'),
    ('62', 'TIGHTED_PUPILS');

DELETE FROM `sbnz_2020`.`vaccine`;
INSERT INTO `sbnz_2020`.`vaccine` (`id`, `name`, `description`)
VALUES 
	('1', 'Rabies vaccine', 'Required vaccine for 12-24 weeks old dog against rabies'),
	('2', 'DHPP', 'Important vaccine for 10-12 weeks old dog. Protects against Hepatitis, Parvovirus and Parainfluence');


INSERT INTO `sbnz_2020`.`vaccination` (`id`, `vaccine_id`, `date`)
VALUES 
	('1', '1', '2018-08-15'),
	('2', '2', '2018-07-25');


DELETE FROM `sbnz_2020`.`ingredient`;
INSERT INTO `sbnz_2020`.`ingredient` (`id`, `name`)
VALUES 
	('1', 'S-adenosylmethionine'),
	('2', 'Silybin'),
	('3', 'Vitamin K1'),
	('4', 'Polysorbate 80'),
	('5', 'Propylene glycol'),
	('6', 'Sodium acetate anhydrous'),
	('7', 'Glacial acetic acid'),
	('8', 'Silymarin');
	
DELETE FROM `sbnz_2020`.`medicine`;
INSERT INTO `sbnz_2020`.`medicine` (`id`, `name`)
VALUES 
	('1', 'Denamarin'),
	('2', 'Vitamin K shot'),
	('3', 'Milk thistle');
	

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
	('11', 'Psychotherapy'),
	('12', 'Milk thistle diet');
	
INSERT INTO `sbnz_2020`.`therapy_medicine` (`therapy_id`, `medicine_id`)
VALUES 
	('1', '1'),
	('2', '2'),
	('12', '3');
	
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
	('10', '7'),
	('9', '12');
	

INSERT INTO `sbnz_2020`.`disease_specific_symptoms` (`disease_id`, `specific_symptoms_id`)
VALUES 
	('1', '35'),
	('2', '35'),
	('3', '36'),
	('3', '37'),
	('3', '13'),
	('3', '23'),
	('4', '16'),
	('4', '11'),
	('4', '38'),
	('4', '36'),
	('4', '14'),
	('5', '1'),
	('5', '39'),
	('6', '16'),
	('6', '22'),
	('7', '40'),
	('7', '41'),
	('7', '42'),
	('7', '43'),
	('8', '44'),
	('9', '45'),
	('10', '46'),
	('10', '1'),
	('10', '2'),
	('10', '12'),
	('10', '24'),
	('10', '47');
	
	
INSERT INTO `sbnz_2020`.`disease_non_specific_symptoms` (`disease_id`, `non_specific_symptoms_id`)
VALUES 
	('1', '23'),
	('1', '13'),
	('1', '6'),
	('1', '12'),
	('1', '48'),
	('1', '1'),
	('2', '49'),
	('2', '50'),
	('2', '51'),
	('2', '28'),
	('3', '14'),
	('3', '12'),
	('3', '1'),
	('3', '52'),
	('3', '16'),
	('3', '53'),
	('3', '2'),
	('3', '51'),
	('3', '10'),
	('4', '1'),
	('4', '2'),
	('4', '15'),
	('4', '9'),
	('4', '53'),
	('4', '54'),
	('5', '30'),
	('5', '2'),
	('5', '55'),
	('5', '3'),
	('5', '56'),
	('5', '16'),
	('5', '24'),
	('6', '1'),
	('6', '2'),
	('6', '12'),
	('7', '58'),
	('7', '57'),
	('8', '1'),
	('8', '2'),
	('8', '31'),
	('8', '59'),
	('8', '11'),
	('8', '60'),
	('8', '61'),
	('9', '1'),
	('9', '13'),
	('9', '23'),
	('9', '3'),
	('10', '23'),
	('10', '6'),
	('10', '55'),
	('10', '4'),
	('10', '16'),
	('10', '62');
	
DELETE FROM `sbnz_2020`.`patient`;

DELETE FROM `sbnz_2020`.`owner`;
INSERT INTO `sbnz_2020`.`owner` (`id`, `first_name`,`last_name`, `city`, `street`, `number`, `phone_num`)
VALUES 
	('1', 'Vasilije', 'Mihajlovic', 'Brcko', 'Vukosavacka', '312', '0038765723743');


INSERT INTO `sbnz_2020`.`patient` (`id`, `name`,`record_number`, `breed`, `date_of_birth`, `owner_id`)
VALUES 
	('1', 'Dzeki', 'REC1', 'MIXEDBREED', '2018-04-07', '1');
	
INSERT INTO `sbnz_2020`.`patient_ingredient_allergies` (`patient_id`, `ingredient_allergies_id`)
VALUES 
	('1', '1');
	
INSERT INTO `sbnz_2020`.`patient_vaccinations` (`patient_id`, `vaccinations_id`)
VALUES 
	('1', '1'),
	('1', '2');
	