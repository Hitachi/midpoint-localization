CREATE OR REPLACE FUNCTION cleanupTestDatabase()
  RETURNS INTEGER AS $$
BEGIN
    TRUNCATE TABLE m_sequence RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_acc_cert_campaign RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_acc_cert_definition RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_audit_delta RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_audit_event RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_object_ext_date RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_object_ext_long RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_object_ext_string RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_object_ext_poly RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_object_ext_reference RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_object_ext_boolean RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_reference RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_assignment_ext_date RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_assignment_ext_long RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_assignment_ext_poly RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_assignment_ext_reference RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_assignment_ext_string RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_assignment_ext_boolean RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_assignment_extension RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_assignment_reference RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_assignment RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_exclusion RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_connector_target_system RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_connector RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_connector_host RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_lookup_table_row RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_lookup_table RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_node RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_shadow RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_task_dependent RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_task RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_object_template RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_value_policy RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_resource RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_user_employee_type RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_user_organization RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_user_organizational_unit RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_focus_photo RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_user RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_report RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_report_output RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_org_org_type RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_org_closure RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_org RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_role RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_abstract_role RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_system_configuration RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_generic_object RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_trigger RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_focus RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_security_policy RESTART IDENTITY CASCADE;
    TRUNCATE TABLE m_object RESTART IDENTITY CASCADE;

  ALTER SEQUENCE hibernate_sequence RESTART WITH 1;

  RETURN 0;
END;
$$ LANGUAGE plpgsql;