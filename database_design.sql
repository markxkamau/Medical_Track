-- Medical Application Database Design

-- 1. Patients Table
CREATE TABLE patient (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    condition VARCHAR(255),
    local_date_time TIMESTAMP,
    photo_id BIGINT
);

-- 2. Photos Table
CREATE TABLE photo (
    id BIGSERIAL PRIMARY KEY,
    image_name VARCHAR(255),
    image_type VARCHAR(255),
    image_data BYTEA
);

ALTER TABLE patient ADD CONSTRAINT fk_patient_photo FOREIGN KEY (photo_id) REFERENCES photo(id);

-- 3. Drugs / Medications Table
CREATE TABLE drug (
    id BIGSERIAL PRIMARY KEY,
    med_id VARCHAR(255) UNIQUE, -- Logical string ID matching frontend (e.g., 'med17')
    name VARCHAR(255),
    purpose VARCHAR(255),
    dosage VARCHAR(255),
    frequency VARCHAR(255),
    prescribed_by VARCHAR(255),
    start_date VARCHAR(255),
    status VARCHAR(255),
    condition_id VARCHAR(255)
);

-- Drug Side Effects (ElementCollection mapping)
CREATE TABLE drug_side_effects (
    drug_id BIGINT NOT NULL,
    side_effects VARCHAR(255),
    FOREIGN KEY (drug_id) REFERENCES drug(id) ON DELETE CASCADE
);

-- 4. Schedules Table
CREATE TABLE schedule (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT,
    medication_id VARCHAR(255),
    medication_name VARCHAR(255),
    dosage VARCHAR(255),
    frequency VARCHAR(255),
    start_date VARCHAR(255),
    end_date VARCHAR(255),
    instructions TEXT,
    -- Logical connections
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    FOREIGN KEY (medication_id) REFERENCES drug(med_id) ON DELETE CASCADE
);

-- Schedule Times (ElementCollection mapping)
CREATE TABLE schedule_times (
    schedule_id BIGINT NOT NULL,
    times VARCHAR(255),
    FOREIGN KEY (schedule_id) REFERENCES schedule(id) ON DELETE CASCADE
);

-- 5. Drug Stock Table
CREATE TABLE drug_stock (
    id BIGSERIAL PRIMARY KEY,
    medication_id VARCHAR(255),
    medication_name VARCHAR(255),
    dosage VARCHAR(255),
    quantity INT,
    unit VARCHAR(255),
    expiration VARCHAR(255),
    location VARCHAR(255),
    batch_number VARCHAR(255),
    reorder_level INT,
    FOREIGN KEY (medication_id) REFERENCES drug(med_id) ON DELETE CASCADE
);

-- 6. Health Records Table
CREATE TABLE health_record (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT,
    date VARCHAR(255),
    blood_pressure VARCHAR(255),
    heart_rate INT,
    temperature DOUBLE PRECISION,
    glucose INT,
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE
);

-- 7. Adherence Table
CREATE TABLE adherence (
    id BIGSERIAL PRIMARY KEY,
    patient_id BIGINT,
    medication_id VARCHAR(255),
    medication_name VARCHAR(255),
    date VARCHAR(255),
    scheduled_time VARCHAR(255),
    taken BOOLEAN,
    taken_time VARCHAR(255),
    notes TEXT,
    FOREIGN KEY (patient_id) REFERENCES patient(id) ON DELETE CASCADE,
    FOREIGN KEY (medication_id) REFERENCES drug(med_id) ON DELETE CASCADE
);
