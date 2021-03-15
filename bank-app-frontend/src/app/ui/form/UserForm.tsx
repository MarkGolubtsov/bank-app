import React, {useContext, useEffect} from 'react';
import {Button, Form, Input, Switch,} from 'antd';
import {User} from 'app/entity/User';
import {RootStoreContext} from 'app/RootStoreContext';
import {StringFormItem} from 'app/ui/form/StringFormItem';
import {DateFormItem} from 'app/ui/form/DateFormItem';
import {observer} from 'mobx-react-lite';
import {SelectFormItem} from 'app/ui/form/SelectFormItem';
import {getDefaultValuesFromObject, getObjectFromValues} from 'app/ui/form/utils';
import moment from 'moment';

interface UserFormProps {
    user?: User,
    onSave: (user: User) => void;
    onCancel: () => void;
}

const NAME = 'name';
const SURNAME = 'surname';
const PATRONYMIC = 'patronymic';
const BIRTHDAY = 'birthday';
const GENDER = 'gender';

const PASSPORT_SERIES = ['passport', 1, 'series'];
const PASSPORT_NUMBER = ['passport', 2, 'number'];
const PASSPORT_ISSUER = ['passport', 3, 'issuedBy'];
const PASSPORT_DATE_OF_ISSUE = ['passport', 4, 'dateOfIssue'];
const PASSPORT_IDENTIFICATION_NUMBER = ['passport', 5, 'identificationNumber'];

const BIRTH_COUNTRY = 'placeOfBirth';

const RESIDENTIAL_ADDRESS_COUNTRY = ['residentialAddress', 1, 'country'];
const RESIDENTIAL_ADDRESS_CITY = ['residentialAddress', 2, 'city'];
const RESIDENTIAL_ADDRESS_STREET = ['residentialAddress', 3, 'street'];
const RESIDENTIAL_ADDRESS_BUILDING_NUMBER = ['residentialAddress', 4, 'buildingNumber'];
const RESIDENTIAL_ADDRESS_APARTMENTS_NUMBER = ['residentialAddress', 5, 'apartmentsNumber'];

const HOME_PHONE = 'homePhoneNumber';
const MOBILE_PHONE = 'mobilePhoneNumber';
const EMAIL = 'email';

const JOB_PLACE = ['work', 0, 'place'];
const JOB_POSITION = ['work', 1, 'position'];

const MARTIAL_STATUS = 'maritalStatus';
const NATIONALITY = 'nationality';
const DISABILITY = 'disability';
const PENSIONER = 'pensioner';
const MONTHLY_INCOME = 'monthlyIncome';
const MILITARY = 'military';


export const UserForm = observer((props: UserFormProps) => {

    const {citiesStore, nationalitiesStore, countries: countriesStore, martialStatusesStore, disabilitiesStore, genderStore} = useContext(RootStoreContext);

    useEffect(() => {
        citiesStore.fetchItems();
        disabilitiesStore.fetchItems();
        martialStatusesStore.fetchItems();
        nationalitiesStore.fetchItems();
        genderStore.fetchItems();
        countriesStore.fetchItems();
    }, [citiesStore, nationalitiesStore, martialStatusesStore, disabilitiesStore, countriesStore, genderStore]);

    const onFinish = (values: any) => {
        let user = getObjectFromValues(values) as User;
        user.residentialAddress.apartmentsNumber = Number(user.residentialAddress.apartmentsNumber);
        user.residentialAddress.buildingNumber = Number(user.residentialAddress.buildingNumber);
        user.monthlyIncome = Number(user.monthlyIncome);
        if (!props.user) {
            props.onSave(user);
        } else {
            user.residentialAddress.id = props.user.residentialAddress.id;
            user.passport.id = props.user.passport.id;
            user.id = props.user.id;
            props.onSave(user);
        }
    };

    const tailLayout = {
        wrapperCol: {offset: 8, span: 16},
    };

    let initialValues = undefined;
    if (props.user) {
        initialValues = getDefaultValuesFromObject(props.user);
        initialValues.birthday = moment(initialValues.birthday);
        initialValues.monthlyIncome = initialValues.monthlyIncome ? String(initialValues.monthlyIncome) : '0';
        initialValues.residentialAddress[4].buildingNumber = initialValues.residentialAddress[4].buildingNumber ? String(initialValues.residentialAddress[4].buildingNumber) : '0';
        initialValues.residentialAddress[5].apartmentsNumber = initialValues.residentialAddress[5].apartmentsNumber ? String(initialValues.residentialAddress[5].apartmentsNumber) : '0';
        initialValues.passport[4].dateOfIssue = moment(initialValues.passport[4].dateOfIssue)
    }

    return (
        <>
            <Form
                labelCol={{span: 4}}
                wrapperCol={{span: 10}}
                layout="horizontal"
                size={'large'}
                initialValues={initialValues}
                onFinish={onFinish}
            >
                <Form.Item name={NAME} label={'Name'} rules={[{
                    required: true,
                    message: 'Required'
                }, {
                    whitespace: true,
                    message: 'Write text.'
                }, {
                    pattern: new RegExp(/[a-zA-Z]+/),
                    message: 'Write english letters'
                }]}>
                    <Input placeholder={'Name'}/>
                </Form.Item>
                <Form.Item name={SURNAME} label={'Surname'} rules={[{
                    required: true,
                    message: 'Required'
                }, {
                    whitespace: true,
                    message: 'Write text.'
                }, {
                    pattern: new RegExp(/[a-zA-Z]+/),
                    message: 'Write english letters'
                }]}>
                    <Input placeholder={'Surname'}/>
                </Form.Item>
                <Form.Item name={PATRONYMIC} label={'Patronymic'} rules={[{
                    required: true,
                    message: 'Required'
                }, {
                    whitespace: true,
                    message: 'Write text.'
                }, {
                    pattern: new RegExp(/[a-zA-Z]+/),
                    message: 'Write english letters'
                }]}>
                    <Input placeholder={'Patronymic'}/>
                </Form.Item>

                <DateFormItem name={BIRTHDAY} shortName={'Birthday'}/>

                <Form.Item valuePropName="checked" name={PENSIONER} label="Pensioner">
                    <Switch defaultChecked={false}/>
                </Form.Item>
                <Form.Item valuePropName="checked" name={MILITARY} label="Military">
                    <Switch/>
                </Form.Item>
                <SelectFormItem items={genderStore.items} name={GENDER} label={'Gender'}/>

                <StringFormItem name={PASSPORT_SERIES}
                                shortName={'Passport series'}/>
                <StringFormItem name={PASSPORT_NUMBER}
                                shortName={'Passport number'}/>
                <StringFormItem name={PASSPORT_ISSUER}
                                shortName={'Passport issued by'}/>
                <DateFormItem name={PASSPORT_DATE_OF_ISSUE}
                              shortName={'Passport date of issued'}/>
                <StringFormItem name={PASSPORT_IDENTIFICATION_NUMBER}
                                shortName={'Passport identification number'}/>

                <SelectFormItem items={countriesStore.items} name={BIRTH_COUNTRY}
                                label={'Birth country'}/>

                <SelectFormItem items={countriesStore.items}
                                name={RESIDENTIAL_ADDRESS_COUNTRY}
                                label={'Residential country'}/>
                <SelectFormItem items={citiesStore.items}
                                name={RESIDENTIAL_ADDRESS_CITY} label={'Residential city'}/>
                <StringFormItem name={RESIDENTIAL_ADDRESS_STREET} shortName={'Residential street'}/>
                <StringFormItem name={RESIDENTIAL_ADDRESS_BUILDING_NUMBER} shortName={'Residential building number'}/>
                <StringFormItem name={RESIDENTIAL_ADDRESS_APARTMENTS_NUMBER} shortName={'Residential apartments number'}
                                isNotRequire={true}/>


                <StringFormItem name={MOBILE_PHONE}
                                shortName={'Mobile phone number'} isNotRequire={true}/>
                <StringFormItem name={HOME_PHONE} shortName={'Home phone number'}
                                isNotRequire={true}/>
                <StringFormItem name={EMAIL} shortName={'Email'} isNotRequire={true}/>

                <StringFormItem name={JOB_PLACE} shortName={'Work place'}
                                isNotRequire={true}/>
                <StringFormItem name={JOB_POSITION} shortName={'Work position'}
                                isNotRequire={true}/>

                <SelectFormItem items={martialStatusesStore.items}
                                name={MARTIAL_STATUS} label={'Martial status'}/>
                <SelectFormItem items={nationalitiesStore.items} name={NATIONALITY}
                                label={'Nationality'}/>

                <SelectFormItem items={disabilitiesStore.items} name={DISABILITY}
                                label={'Disability'}/>
                <StringFormItem name={MONTHLY_INCOME} shortName={'Monthly income'} isNotRequire={true}/>
                <Form.Item {...tailLayout}>
                    <Button type="primary" htmlType="submit">Save</Button>
                </Form.Item>
            </Form>
        </>)
})