/**
 */
package org.openscada.contrib.da.server.exporter.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.openscada.contrib.da.server.exporter.ExportType;
import org.openscada.contrib.da.server.exporter.ExporterPackage;
import org.openscada.contrib.da.server.exporter.HiveConfigurationType;
import org.openscada.contrib.da.server.exporter.HiveType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Hive Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.openscada.contrib.da.server.exporter.impl.HiveTypeImpl#getConfiguration <em>Configuration</em>}</li>
 *   <li>{@link org.openscada.contrib.da.server.exporter.impl.HiveTypeImpl#getExport <em>Export</em>}</li>
 *   <li>{@link org.openscada.contrib.da.server.exporter.impl.HiveTypeImpl#getFactory <em>Factory</em>}</li>
 *   <li>{@link org.openscada.contrib.da.server.exporter.impl.HiveTypeImpl#getRef <em>Ref</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class HiveTypeImpl extends EObjectImpl implements HiveType
{
    /**
     * The cached value of the '{@link #getConfiguration() <em>Configuration</em>}' containment reference.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getConfiguration()
     * @generated
     * @ordered
     */
    protected HiveConfigurationType configuration;

    /**
     * The cached value of the '{@link #getExport() <em>Export</em>}' containment reference list.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getExport()
     * @generated
     * @ordered
     */
    protected EList<ExportType> export;

    /**
     * The default value of the '{@link #getFactory() <em>Factory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFactory()
     * @generated
     * @ordered
     */
    protected static final String FACTORY_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getFactory() <em>Factory</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getFactory()
     * @generated
     * @ordered
     */
    protected String factory = FACTORY_EDEFAULT;

    /**
     * The default value of the '{@link #getRef() <em>Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRef()
     * @generated
     * @ordered
     */
    protected static final String REF_EDEFAULT = null;

    /**
     * The cached value of the '{@link #getRef() <em>Ref</em>}' attribute.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see #getRef()
     * @generated
     * @ordered
     */
    protected String ref = REF_EDEFAULT;

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    protected HiveTypeImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected EClass eStaticClass()
    {
        return ExporterPackage.Literals.HIVE_TYPE;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HiveConfigurationType getConfiguration()
    {
        return configuration;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public NotificationChain basicSetConfiguration(HiveConfigurationType newConfiguration, NotificationChain msgs)
    {
        HiveConfigurationType oldConfiguration = configuration;
        configuration = newConfiguration;
        if (eNotificationRequired())
        {
            ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ExporterPackage.HIVE_TYPE__CONFIGURATION, oldConfiguration, newConfiguration);
            if (msgs == null) msgs = notification; else msgs.add(notification);
        }
        return msgs;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setConfiguration(HiveConfigurationType newConfiguration)
    {
        if (newConfiguration != configuration)
        {
            NotificationChain msgs = null;
            if (configuration != null)
                msgs = ((InternalEObject)configuration).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ExporterPackage.HIVE_TYPE__CONFIGURATION, null, msgs);
            if (newConfiguration != null)
                msgs = ((InternalEObject)newConfiguration).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ExporterPackage.HIVE_TYPE__CONFIGURATION, null, msgs);
            msgs = basicSetConfiguration(newConfiguration, msgs);
            if (msgs != null) msgs.dispatch();
        }
        else if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExporterPackage.HIVE_TYPE__CONFIGURATION, newConfiguration, newConfiguration));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public EList<ExportType> getExport()
    {
        if (export == null)
        {
            export = new EObjectContainmentEList<ExportType>(ExportType.class, this, ExporterPackage.HIVE_TYPE__EXPORT);
        }
        return export;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getFactory()
    {
        return factory;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setFactory(String newFactory)
    {
        String oldFactory = factory;
        factory = newFactory;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExporterPackage.HIVE_TYPE__FACTORY, oldFactory, factory));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public String getRef()
    {
        return ref;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public void setRef(String newRef)
    {
        String oldRef = ref;
        ref = newRef;
        if (eNotificationRequired())
            eNotify(new ENotificationImpl(this, Notification.SET, ExporterPackage.HIVE_TYPE__REF, oldRef, ref));
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
    {
        switch (featureID)
        {
            case ExporterPackage.HIVE_TYPE__CONFIGURATION:
                return basicSetConfiguration(null, msgs);
            case ExporterPackage.HIVE_TYPE__EXPORT:
                return ((InternalEList<?>)getExport()).basicRemove(otherEnd, msgs);
        }
        return super.eInverseRemove(otherEnd, featureID, msgs);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public Object eGet(int featureID, boolean resolve, boolean coreType)
    {
        switch (featureID)
        {
            case ExporterPackage.HIVE_TYPE__CONFIGURATION:
                return getConfiguration();
            case ExporterPackage.HIVE_TYPE__EXPORT:
                return getExport();
            case ExporterPackage.HIVE_TYPE__FACTORY:
                return getFactory();
            case ExporterPackage.HIVE_TYPE__REF:
                return getRef();
        }
        return super.eGet(featureID, resolve, coreType);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @SuppressWarnings("unchecked")
    @Override
    public void eSet(int featureID, Object newValue)
    {
        switch (featureID)
        {
            case ExporterPackage.HIVE_TYPE__CONFIGURATION:
                setConfiguration((HiveConfigurationType)newValue);
                return;
            case ExporterPackage.HIVE_TYPE__EXPORT:
                getExport().clear();
                getExport().addAll((Collection<? extends ExportType>)newValue);
                return;
            case ExporterPackage.HIVE_TYPE__FACTORY:
                setFactory((String)newValue);
                return;
            case ExporterPackage.HIVE_TYPE__REF:
                setRef((String)newValue);
                return;
        }
        super.eSet(featureID, newValue);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public void eUnset(int featureID)
    {
        switch (featureID)
        {
            case ExporterPackage.HIVE_TYPE__CONFIGURATION:
                setConfiguration((HiveConfigurationType)null);
                return;
            case ExporterPackage.HIVE_TYPE__EXPORT:
                getExport().clear();
                return;
            case ExporterPackage.HIVE_TYPE__FACTORY:
                setFactory(FACTORY_EDEFAULT);
                return;
            case ExporterPackage.HIVE_TYPE__REF:
                setRef(REF_EDEFAULT);
                return;
        }
        super.eUnset(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public boolean eIsSet(int featureID)
    {
        switch (featureID)
        {
            case ExporterPackage.HIVE_TYPE__CONFIGURATION:
                return configuration != null;
            case ExporterPackage.HIVE_TYPE__EXPORT:
                return export != null && !export.isEmpty();
            case ExporterPackage.HIVE_TYPE__FACTORY:
                return FACTORY_EDEFAULT == null ? factory != null : !FACTORY_EDEFAULT.equals(factory);
            case ExporterPackage.HIVE_TYPE__REF:
                return REF_EDEFAULT == null ? ref != null : !REF_EDEFAULT.equals(ref);
        }
        return super.eIsSet(featureID);
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public String toString()
    {
        if (eIsProxy()) return super.toString();

        StringBuffer result = new StringBuffer(super.toString());
        result.append(" (factory: ");
        result.append(factory);
        result.append(", ref: ");
        result.append(ref);
        result.append(')');
        return result.toString();
    }

} //HiveTypeImpl
