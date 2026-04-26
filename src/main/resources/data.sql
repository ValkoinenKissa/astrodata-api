-- =============================================================
-- AstroData API — Seed Data
-- Real astronomical data from NASA Exoplanet Archive & ESA
-- Uses INSERT IGNORE to avoid duplicates on restart
-- =============================================================

-- -------------------------------------------------------------
-- SPACE MISSIONS
-- -------------------------------------------------------------
INSERT IGNORE INTO space_missions (id, name, agency, launch_date, status, description, date_created, last_updated) VALUES
                                                                                                                       (1, 'Kepler', 'NASA', '2009-03-07', 'COMPLETED', 'Space telescope that discovered over 2,600 confirmed exoplanets using the transit method. Revolutionized our understanding of planetary systems.', NOW(), NOW()),
                                                                                                                       (2, 'TESS', 'NASA', '2018-04-18', 'ACTIVE', 'Transiting Exoplanet Survey Satellite. Surveys the entire sky searching for exoplanets around nearby bright stars.', NOW(), NOW()),
                                                                                                                       (3, 'James Webb Space Telescope', 'NASA/ESA/CSA', '2021-12-25', 'ACTIVE', 'Most powerful space telescope ever built. Observes in infrared, capable of studying exoplanet atmospheres in detail.', NOW(), NOW()),
                                                                                                                       (4, 'Hubble Space Telescope', 'NASA/ESA', '1990-04-24', 'ACTIVE', 'Iconic space telescope operating for over 30 years. Has contributed to thousands of scientific discoveries.', NOW(), NOW()),
                                                                                                                       (5, 'Gaia', 'ESA', '2013-12-19', 'ACTIVE', 'ESA mission mapping the Milky Way in 3D. Has catalogued over 1.8 billion stars with unprecedented precision.', NOW(), NOW()),
                                                                                                                       (6, 'Chandra X-ray Observatory', 'NASA', '1999-07-23', 'ACTIVE', 'Space telescope observing X-rays from high-energy regions of the universe such as supernova remnants and black holes.', NOW(), NOW()),
                                                                                                                       (7, 'CHEOPS', 'ESA', '2019-12-18', 'ACTIVE', 'CHaracterising ExOPlanet Satellite. ESA mission to characterize exoplanets already known to orbit nearby stars.', NOW(), NOW());

-- -------------------------------------------------------------
-- STARS
-- -------------------------------------------------------------
INSERT IGNORE INTO stars (id, name, catalogue_id, spectral_type, mass_solar, radius_solar, temperaturek, distance_ly, constellation, date_created, last_updated) VALUES
                                                                                                                                                                     (1,  'Proxima Centauri', 'GJ 551',                  'M5.5Ve', 0.1221, 0.1542, 3042.0, 4.24,   'Centaurus', NOW(), NOW()),
                                                                                                                                                                     (2,  'TRAPPIST-1',       '2MASS J23062928-0502285', 'M8V',    0.0898, 0.1192, 2566.0, 40.66,  'Aquarius',  NOW(), NOW()),
                                                                                                                                                                     (3,  '51 Pegasi',        'HD 217014',               'G2IV',   1.11,   1.237,  5793.0, 50.45,  'Pegasus',   NOW(), NOW()),
                                                                                                                                                                     (4,  'Kepler-452',       'KIC 8311864',             'G2V',    1.037,  1.11,   5757.0, 1400.0, 'Cygnus',    NOW(), NOW()),
                                                                                                                                                                     (5,  'HD 209458',        'HD 209458',               'G0V',    1.148,  1.203,  6117.0, 159.0,  'Pegasus',   NOW(), NOW()),
                                                                                                                                                                     (6,  'Tau Ceti',         'HD 10700',                'G8.5V',  0.783,  0.793,  5344.0, 11.91,  'Cetus',     NOW(), NOW()),
                                                                                                                                                                     (7,  'Kepler-22',        'KIC 10593626',            'G5V',    0.97,   0.979,  5518.0, 619.0,  'Cygnus',    NOW(), NOW()),
                                                                                                                                                                     (8,  'GJ 1214',          'GJ 1214',                 'M4.5V',  0.157,  0.211,  3026.0, 47.0,   'Ophiuchus', NOW(), NOW()),
                                                                                                                                                                     (9,  'Beta Pictoris',    'HD 39060',                'A6V',    1.797,  1.728,  8052.0, 63.4,   'Pictor',    NOW(), NOW()),
                                                                                                                                                                     (10, 'HR 8799',          'HD 218396',               'F0V',    1.516,  1.44,   7430.0, 129.0,  'Pegasus',   NOW(), NOW());

-- -------------------------------------------------------------
-- EXOPLANETS
-- -------------------------------------------------------------
INSERT IGNORE INTO exoplanets (id, name, mass_earth, radius_earth, orbital_period_days, discovery_year, is_in_habitable_zone, star_id, date_created, last_updated) VALUES
-- Proxima Centauri system
(1,  'Proxima Centauri b', 1.27,   1.1,   11.186,   2016, TRUE,  1, NOW(), NOW()),
(2,  'Proxima Centauri d', 0.26,   0.81,  5.122,    2022, FALSE, 1, NOW(), NOW()),
-- TRAPPIST-1 system
(3,  'TRAPPIST-1b',        1.017,  1.116, 1.511,    2017, FALSE, 2, NOW(), NOW()),
(4,  'TRAPPIST-1c',        1.156,  1.097, 2.422,    2017, FALSE, 2, NOW(), NOW()),
(5,  'TRAPPIST-1d',        0.297,  0.788, 4.050,    2017, FALSE, 2, NOW(), NOW()),
(6,  'TRAPPIST-1e',        0.772,  0.920, 6.101,    2017, TRUE,  2, NOW(), NOW()),
(7,  'TRAPPIST-1f',        0.934,  1.045, 9.207,    2017, TRUE,  2, NOW(), NOW()),
(8,  'TRAPPIST-1g',        1.148,  1.129, 12.353,   2017, TRUE,  2, NOW(), NOW()),
-- 51 Pegasi system
(9,  '51 Pegasi b',        149.0,  12.8,  4.231,    1995, FALSE, 3, NOW(), NOW()),
-- Kepler-452 system
(10, 'Kepler-452b',        5.0,    1.63,  384.843,  2015, TRUE,  4, NOW(), NOW()),
-- HD 209458 system
(11, 'HD 209458 b',        219.0,  15.4,  3.525,    1999, FALSE, 5, NOW(), NOW()),
-- Tau Ceti system
(12, 'Tau Ceti e',         3.93,   1.58,  162.87,   2017, TRUE,  6, NOW(), NOW()),
(13, 'Tau Ceti f',         3.93,   1.58,  636.13,   2017, TRUE,  6, NOW(), NOW()),
-- Kepler-22 system
(14, 'Kepler-22b',         9.1,    2.38,  289.864,  2011, TRUE,  7, NOW(), NOW()),
-- GJ 1214 system
(15, 'GJ 1214 b',          6.55,   2.678, 1.580,    2009, FALSE, 8, NOW(), NOW()),
-- Beta Pictoris system
(16, 'Beta Pictoris b',    4169.0, 12.0,  8955.0,   2008, FALSE, 9, NOW(), NOW()),
(17, 'Beta Pictoris c',    910.0,  9.0,   1250.0,   2019, FALSE, 9, NOW(), NOW()),
-- HR 8799 system
(18, 'HR 8799 b',          2228.0, 13.0,  164250.0, 2008, FALSE, 10, NOW(), NOW()),
(19, 'HR 8799 c',          3178.0, 13.0,  73000.0,  2008, FALSE, 10, NOW(), NOW()),
(20, 'HR 8799 d',          3178.0, 13.0,  36500.0,  2008, FALSE, 10, NOW(), NOW());

-- -------------------------------------------------------------
-- COSMIC EVENTS
-- -------------------------------------------------------------
INSERT IGNORE INTO cosmic_events (id, name, event_type, event_date, magnitude, description, star_id, date_created, last_updated) VALUES
                                                                                                                                     (1, 'SN 1987A',                     'SUPERNOVA',           '1987-02-23', -3.0, 'Closest observed supernova since the invention of the telescope. Located in the Large Magellanic Cloud.', NULL, NOW(), NOW()),
                                                                                                                                     (2, 'GRB 080319B',                  'GAMMA_RAY_BURST',     '2008-03-19', 5.7,  'Naked-eye gamma-ray burst, briefly the most luminous object ever observed. Peak luminosity exceeded entire Milky Way by factor of 2.5 million.', NULL, NOW(), NOW()),
                                                                                                                                     (3, 'HD 209458 b Transit',          'PLANETARY_TRANSIT',   '1999-11-07', NULL, 'First confirmed transiting exoplanet. Allowed direct measurement of atmospheric composition via transmission spectroscopy.', 5, NOW(), NOW()),
                                                                                                                                     (4, 'TRAPPIST-1e Transit',          'PLANETARY_TRANSIT',   '2017-02-22', NULL, 'Transit observation of TRAPPIST-1e, a potentially habitable world. Studied intensively by James Webb Space Telescope.', 2, NOW(), NOW()),
                                                                                                                                     (5, 'Proxima Centauri Superflare',  'STELLAR_FLARE',       '2016-03-18', NULL, 'Massive X-ray flare from Proxima Centauri, 10 times more powerful than the largest solar flares. May affect habitability of Proxima b.', 1, NOW(), NOW()),
                                                                                                                                     (6, 'Beta Pictoris b Direct Image', 'DIRECT_IMAGING',      '2008-11-13', NULL, 'First direct image of Beta Pictoris b, one of the first exoplanets ever directly photographed. Confirmed planet formation around young stars.', 9, NOW(), NOW()),
                                                                                                                                     (7, 'Oumuamua Detection',           'INTERSTELLAR_OBJECT', '2017-10-19', 22.0, 'First detected interstellar object passing through the Solar System. Unusual acceleration suggested possible exotic composition.', NULL, NOW(), NOW()),
                                                                                                                                     (8, 'GW170817',                     'GRAVITATIONAL_WAVE',  '2017-08-17', NULL, 'First gravitational wave detection from a neutron star merger, also observed as kilonova. Confirmed gold and heavy elements form in neutron star collisions.', NULL, NOW(), NOW());

-- -------------------------------------------------------------
-- MANY-TO-MANY: STAR <-> SPACE_MISSION
-- -------------------------------------------------------------
INSERT IGNORE INTO star_space_missions (star_id, space_mission_id) VALUES
                                                                       (4, 1),   -- Kepler-452 → Kepler
                                                                       (7, 1),   -- Kepler-22 → Kepler
                                                                       (1, 2),   -- Proxima Centauri → TESS
                                                                       (2, 2),   -- TRAPPIST-1 → TESS
                                                                       (6, 2),   -- Tau Ceti → TESS
                                                                       (2, 3),   -- TRAPPIST-1 → JWST
                                                                       (5, 3),   -- HD 209458 → JWST
                                                                       (1, 3),   -- Proxima Centauri → JWST
                                                                       (5, 4),   -- HD 209458 → Hubble
                                                                       (9, 4),   -- Beta Pictoris → Hubble
                                                                       (10, 4),  -- HR 8799 → Hubble
                                                                       (1, 5),   -- Proxima Centauri → Gaia
                                                                       (3, 5),   -- 51 Pegasi → Gaia
                                                                       (6, 5),   -- Tau Ceti → Gaia
                                                                       (8, 5),   -- GJ 1214 → Gaia
                                                                       (2, 7),   -- TRAPPIST-1 → CHEOPS
                                                                       (5, 7),   -- HD 209458 → CHEOPS
                                                                       (8, 7);   -- GJ 1214 → CHEOPS

-- -------------------------------------------------------------
-- MANY-TO-MANY: EXOPLANET <-> SPACE_MISSION
-- -------------------------------------------------------------
INSERT IGNORE INTO exoplanet_space_missions (exoplanet_id, space_mission_id) VALUES
                                                                                 (10, 1),  -- Kepler-452b → Kepler
                                                                                 (14, 1),  -- Kepler-22b → Kepler
                                                                                 (1, 2),   -- Proxima Centauri b → TESS
                                                                                 (2, 2),   -- Proxima Centauri d → TESS
                                                                                 (12, 2),  -- Tau Ceti e → TESS
                                                                                 (13, 2),  -- Tau Ceti f → TESS
                                                                                 (6, 3),   -- TRAPPIST-1e → JWST
                                                                                 (7, 3),   -- TRAPPIST-1f → JWST
                                                                                 (11, 3),  -- HD 209458 b → JWST
                                                                                 (3, 3),   -- TRAPPIST-1b → JWST
                                                                                 (4, 3),   -- TRAPPIST-1c → JWST
                                                                                 (11, 4),  -- HD 209458 b → Hubble
                                                                                 (15, 4),  -- GJ 1214 b → Hubble
                                                                                 (16, 4),  -- Beta Pictoris b → Hubble
                                                                                 (11, 7),  -- HD 209458 b → CHEOPS
                                                                                 (15, 7),  -- GJ 1214 b → CHEOPS
                                                                                 (6, 7);   -- TRAPPIST-1e → CHEOPS