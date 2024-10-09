INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('1', 'GNDR', 'Gender', 'list of values describing human gender/sex', 'gender', null, false, null,
        null, null, null, null, 1700414302834, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('2', 'CNTRY', 'Country', 'worldwide list of countries', 'country', null, false, null, null, null,
        null, null, 1700416811307, null, false);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('9143385586600', 'CITY', 'City', 'list of cities within a country', null, '2', false, null, null,
        null, null, null, 1700417534458, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('864455517450500', 'DOC_CNTRY', 'Document Countrly', null, null, null, false, null, null, null, null,
        1701272167166, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('864468658054900', 'DOC_TYPE', 'Document Type', null, null, null, false, null, null, null, null,
        1701272180307, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('864503364894600', 'MARI_STATUS', 'Marital Status', null, null, null, false, null, null, null, null,
        1701272215013, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('864511906012300', 'NAT', 'Nationality', null, null, null, false, null, null, null, null, 1701272223555,
        null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('864528997407800', 'LANG', 'Language', null, null, null, false, null, null, null, null, 1701272240646,
        null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('864539203829800', 'REL', 'Religeon', null, null, null, false, null, null, null, null, 1701272250852,
        null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('864549986191300', 'ETH', 'Ethnicity', null, null, null, false, null, null, null, null, 1701272261635,
        null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('864564826818600', 'OCCP', 'Occupation', null, null, null, false, null, null, null, null, 1701272276475,
        null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('883618930104800', 'RELATION', 'Relation', null, null, null, false, null, null, null, null, null,
        1701291342433, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91051863477500', 'ENC_STATUS', 'Encounter Status', null, null, null, false, null, null, null, null,
        1703102222448, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91238225394900', 'ENC_CLASS', 'Encounter Class', null, null, null, false, null, null, null, null,
        1703102408811, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91297236097900', 'ENC_PRIORITY', 'Encounter Priority', null, null, null, false, null, null, null, null,
        1703102467821, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91396092675600', 'ENC_TYPE', 'Encounter Type', null, null, null, false, null, null, null, null,
        1703102566678, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91448322909300', 'SERVICE_TYPE', 'Service Type', null, null, null, false, null, null, null, null,
        1703102618908, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91486112884100', 'PATIENT_STATUS', 'Patient Status', null, null, null, false, null, null, null, null,
        1703102656698, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91538808602000', 'ENC_BASED_ON', 'Encounter Based On', null, null, null, false, null, null, null, null,
        1703102709394, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91576766345900', 'ENC_REASON', 'Encounter Reason', null, null, null, false, null, null, null, null,
        1703102747351, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91599846466900', 'DIET_PREF', 'Diet Preference', null, null, null, false, null, null, null, null,
        1703102770431, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91733593642300', 'ENC_SPECIAL_ARNG', 'Encounter Special Arrangements', null, null, null, false, null,
        null, null, null, 1703102904179, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91795929550100', 'ENC_SPECIAL_COURT', 'Encounter Special Courtesy', null, null, null, false, null, null,
        null, null, 1703102966515, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91817600900100', 'LOCATION_TYPE', 'Location Type', null, null, null, false, null, null, null, null,
        1703102988186, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91833861713600', 'PAYMENT_TYPE', 'Payment Type', null, null, null, false, null, null, null, null,
        1703103004447, null, null, true);
INSERT INTO public.ap_lov (key, lov_code, lov_name, lov_description, love_custom_code, parent_lov, auto_select_default,
                           default_value_id, created_by, updated_by, deleted_by, created_at, updated_at, deleted_at,
                           is_valid)
VALUES ('91891233782700', 'PAYER_TYPE', 'Payer Type', null, null, null, false, null, null, null, null,
        1703103061819, null, null, true);