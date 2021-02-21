import React, {useContext, useEffect} from 'react';
import {Button, Form, Switch,} from 'antd';
import {User} from 'app/entity/User';
import {RootStoreContext} from 'app/RootStoreContext';
import {StringFormItem} from 'app/ui/form/StringFormItem';
import {DateFormItem} from 'app/ui/form/DateFormItem';
import {observer} from 'mobx-react-lite';
import {SelectFormItem} from 'app/ui/form/SelectFormItem';
import {getDefaultValuesFromObject, getObjectFromValues} from 'app/ui/form/utils';

interface UserFormProps {
    user?: User,
    onSave: (user: User) => void;
    onCancel: () => void;
}

const NAME = 'name';
const SURNAME = 'surname';
const PATRONYMIC = 'patronymic';
const BIRTHDAY = 'birthday';
const PENSIONER = 'pensioner';

const PLACE_OF_BIRTH = 'placeOfBirth';
const NATIONALITY = ['nationality', 0, '_id'];
const CITY_OF_ACTUAL_RESIDENCE = ['cityOfActualResidence', 0, '_id'];
const ADDRESS_OF_ACTUAL_RESIDENCE = 'addressOfActualResidence';

const MOBILE_PHONE = 'mobilePhoneNumber';
const HOME_PHONE = 'homePhoneNumber';
const EMAIL = 'email';

const MONTHLY_INCOME = 'monthlyIncome';

const MARTIAL_STATUSES = ['maritalStatus', 0, '_id'];
const DISABILITY = ['disability', 0, '_id'];

const MILITARY = 'military';

const PASSPORT_SERIES = ['passport', 0, 'series'];
const PASSPORT_NUMBER = ['passport', 1, 'number'];
const PASSPORT_ISSUED_BY = ['passport', 2, 'issuedBy'];
const PASSPORT_DATE_OF_ISSUE = ['passport', 3, 'dateOfIssue'];
const PASSPORT_IDENTIFICATION_NUMBER = ['passport', 4, 'identificationNumber'];

const WORK_PLACE = ['work', 0, 'place'];
const WORK_POSITION = ['work', 1, 'position'];

export const UserForm = observer((props: UserFormProps) => {

    const {citiesStore, nationalitiesStore, martialStatusesStore, disabilitiesStore} = useContext(RootStoreContext);

    useEffect(() => {
        citiesStore.fetchItems();
        disabilitiesStore.fetchItems();
        martialStatusesStore.fetchItems();
        disabilitiesStore.fetchItems();
        nationalitiesStore.fetchItems();
    }, [citiesStore, nationalitiesStore, martialStatusesStore, disabilitiesStore]);

    //todo created save user endpoint
    //todo created default values
    const onFinish = (values: any) => {
        console.log('Success:', values);
        props.onSave(getObjectFromValues(values) as User);
    };

    const tailLayout = {
        wrapperCol: {offset: 8, span: 16},
    };
    return (
        <>
            <Form
                labelCol={{span: 4}}
                wrapperCol={{span: 10}}
                layout="horizontal"
                size={'large'}
                initialValues={getDefaultValuesFromObject(props.user)}
                onFinish={onFinish}
            >
                <StringFormItem name={NAME}/>
                <StringFormItem name={SURNAME}/>
                <StringFormItem name={PATRONYMIC}/>

                <DateFormItem name={BIRTHDAY} shortName={'Birthday'}/>

                <Form.Item initialValue={false} valuePropName="checked" name={PENSIONER} label="Pensioner">
                    <Switch defaultChecked={false}/>
                </Form.Item>
                <Form.Item initialValue={false} valuePropName="checked" name={MILITARY} label="Military">
                    <Switch/>
                </Form.Item>
                <SelectFormItem name={NATIONALITY} label={'Nationality'}
                                items={nationalitiesStore.items}/>
                <SelectFormItem name={MARTIAL_STATUSES} label={'Martial status'}
                                items={martialStatusesStore.items}/>
                <SelectFormItem name={DISABILITY} label={'Disability'}
                                items={disabilitiesStore.items}/>
                <SelectFormItem name={CITY_OF_ACTUAL_RESIDENCE} label={'City of actual residence'}
                                items={citiesStore.items}/>
                <StringFormItem name={ADDRESS_OF_ACTUAL_RESIDENCE} shortName={'Address of actual residence'}/>
                <StringFormItem name={PLACE_OF_BIRTH} shortName={'Place of birth'}/>
                <StringFormItem name={PASSPORT_SERIES} shortName={'Passport series'}/>
                <StringFormItem name={PASSPORT_NUMBER} shortName={'Passport number'}/>
                <StringFormItem name={PASSPORT_ISSUED_BY} shortName={'Passport issued By'}/>
                <DateFormItem name={PASSPORT_DATE_OF_ISSUE} shortName={'Passport date of issue'}/>
                <StringFormItem name={PASSPORT_IDENTIFICATION_NUMBER} shortName={'Passport identification number'}/>
                <StringFormItem name={WORK_PLACE} shortName={'Work place'} isNotRequire={true}/>
                <StringFormItem name={WORK_POSITION} shortName={'Work position'} isNotRequire={true}/>
                <StringFormItem name={MOBILE_PHONE} shortName={'Mobile phone number'} isNotRequire={true}/>
                <StringFormItem name={HOME_PHONE} shortName={'Home phone number'} isNotRequire={true}/>
                <StringFormItem name={EMAIL} shortName={'Email'} isNotRequire={true}/>
                <StringFormItem name={MONTHLY_INCOME} shortName={'Monthly income'} isNotRequire={true}/>
                <Form.Item {...tailLayout}>
                    <Button type="primary" htmlType="submit">Save</Button>
                </Form.Item>
            </Form>
        </>)
})