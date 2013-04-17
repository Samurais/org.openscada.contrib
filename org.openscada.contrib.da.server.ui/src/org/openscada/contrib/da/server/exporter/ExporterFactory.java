/**
 */
package org.openscada.contrib.da.server.exporter;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.openscada.contrib.da.server.exporter.ExporterPackage
 * @generated
 */
public interface ExporterFactory extends EFactory
{
    /**
     * The singleton instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    ExporterFactory eINSTANCE = org.openscada.contrib.da.server.exporter.impl.ExporterFactoryImpl.init();

    /**
     * Returns a new object of class '<em>Announcer Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Announcer Type</em>'.
     * @generated
     */
    AnnouncerType createAnnouncerType();

    /**
     * Returns a new object of class '<em>Configuration Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Configuration Type</em>'.
     * @generated
     */
    ConfigurationType createConfigurationType();

    /**
     * Returns a new object of class '<em>Document Root</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Document Root</em>'.
     * @generated
     */
    DocumentRoot createDocumentRoot();

    /**
     * Returns a new object of class '<em>Export Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Export Type</em>'.
     * @generated
     */
    ExportType createExportType();

    /**
     * Returns a new object of class '<em>Hive Configuration Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Hive Configuration Type</em>'.
     * @generated
     */
    HiveConfigurationType createHiveConfigurationType();

    /**
     * Returns a new object of class '<em>Hive Type</em>'.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return a new object of class '<em>Hive Type</em>'.
     * @generated
     */
    HiveType createHiveType();

    /**
     * Returns the package supported by this factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @return the package supported by this factory.
     * @generated
     */
    ExporterPackage getExporterPackage();

} //ExporterFactory
