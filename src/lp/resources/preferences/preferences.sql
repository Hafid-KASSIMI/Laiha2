BEGIN TRANSACTION;

-- Table preferences
CREATE TABLE IF NOT EXISTS `preferences` ( 
    `id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    `libelle` VARCHAR ( 128 ),
    `value` TEXT
);

COMMIT;