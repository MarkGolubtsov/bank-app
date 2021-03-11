import React, {useContext, useEffect, useState} from 'react';
import {Button, DatePicker, Form, Input, InputNumber, notification, Select} from 'antd';
import moment from 'moment';
import {RootStoreContext} from 'app/RootStoreContext';
import {observer} from 'mobx-react-lite';

const {RangePicker} = DatePicker;

type DepType = {
    id: number,
    name: string,
    countDays: CountType[];
    type: TypeCount
}

type TypeCount = 'd' | 'months';
type CountType = {
    value: number,
    procent: number
}
const depositsPlans: DepType[] = [
    {
        id: 1,
        name: 'First',
        countDays: [{
            value: 100,
            procent: 17.0
        },
            {
                value: 3,
                procent: 17.0
            }],
        type: 'd'
    },
    {
        id: 2,
        name: 'Last',
        countDays: [{
            value: 6,
            procent: 12.5
        }, {
            value: 18,
            procent: 13.0
        }],
        type: 'months'
    }
];

type DepositAgreementFormPops = {
    onCreate: (data: any) => void;
};

interface map {
    [key: string]: string
}

export const DepositAgreementForm = observer(({onCreate}: DepositAgreementFormPops) => {
    const {currency} = useContext(RootStoreContext);

    useEffect(() => {
        currency.fetchItems();
    }, [currency]);
    const [depositProgramValue, setDepositProgramValue] = useState<DepType | undefined>(undefined);
    const [currencyValue, setCurrencyValue] = useState<string | undefined>(undefined);
    const [startDateValue, setStartDateValue] = useState<moment.Moment>(moment());
    const [endDateValue, setEndDateValue] = useState<moment.Moment>(moment().add(1, 'd'));

    const [countsValue, setCountValue] = useState<CountType | undefined>(undefined);
    const [amountMoney, setAmountMoney] = useState<number>(50);
    const depositPrograms: DepType[] = depositsPlans;

    const tailLayout = {
        wrapperCol: {offset: 8, span: 16},
    };

    const onSave = () => {
        if (depositProgramValue?.id && currencyValue && currency && countsValue) {
            const deposit = {
                programId: depositProgramValue?.id,
                currency: currencyValue,
                startDate: startDateValue.toDate(),
                endDate: endDateValue.toDate(),
                percents: countsValue.procent,
                amount: amountMoney
            }
            onCreate(deposit);
        } else {
            notification.error({
                message: 'Check values',
                placement: 'bottomLeft'
            })
        }
    }

    const countDays: CountType[] = depositProgramValue?.countDays ?? [];

    const handleChangeDepProg = (value?: DepType) => {
        setDepositProgramValue(value);
        const countDay = value?.countDays[0];
        setCountValue(countDay);
        if (value && countDay) {
            setEndDateValue(startDateValue.clone().add(countDay.value, value.type));
        }
    };

    const handleChangeCountsDayValue = (value: number) => {
        setCountValue(depositProgramValue?.countDays.find(c => c.value == value));
        if (depositProgramValue) {
            setEndDateValue(startDateValue.clone().add(value, depositProgramValue.type));
        }
    };

    const countMessageType = depositProgramValue ? depositProgramValue?.type === 'months' ? 'month' : 'days' : '';

    return (
        <Form
            labelCol={{span: 4}}
            wrapperCol={{span: 10}}
            layout="horizontal"
            size={'large'}
            onFinish={onSave}
        >
            <Form.Item label={'Deposit program'} help={depositProgramValue ? '' : 'Choose deposit program'}
                       validateStatus={depositProgramValue ? 'success' : 'error'}>
                <Select
                    onChange={(value) => handleChangeDepProg(depositPrograms.find(dep => dep.id === Number(value)))}
                    value={depositProgramValue?.name}>
                    {
                        depositPrograms.map((value) => {
                            return <Select.Option key={value.id} value={value.id}>{value.name}</Select.Option>
                        })
                    }
                </Select>
            </Form.Item>

            <Form.Item label={`Count ${countMessageType}`} help={countMessageType ? '' : 'Choose count'}
                       validateStatus={countMessageType ? 'success' : 'error'}>
                <Select onChange={(value) => handleChangeCountsDayValue(value)} value={countsValue?.value}>
                    {
                        countDays.map((value) => {
                            return <Select.Option key={value.value}
                                                  value={value.value}>{value.value} {countMessageType}</Select.Option>
                        })
                    }
                </Select>
            </Form.Item>

            <Form.Item label={'Deposit interest:'}>
                <Input disabled value={countsValue?.procent ? `${countsValue.procent}%` : ''}/>
            </Form.Item>

            <Form.Item label={'Currency'} help={currencyValue ? '' : 'Choose currency'}
                       validateStatus={currencyValue ? 'success' : 'error'}>
                <Select onChange={(value) => setCurrencyValue(value)} value={currencyValue}>
                    {
                        currency.items.map((value) => {
                            return <Select.Option key={value} value={value}>{value}</Select.Option>
                        })
                    }
                </Select>
            </Form.Item>

            <Form.Item label={'Start deposit program'}>
                <DatePicker value={startDateValue} onChange={(date) => {
                    if (date) {
                        setStartDateValue(date);
                        if (depositProgramValue && countsValue) {
                            setEndDateValue(date.clone().add(countsValue?.value, depositProgramValue.type));
                        }
                    } else {
                        setStartDateValue(moment());
                        if (depositProgramValue && countsValue) {
                            setEndDateValue(moment().add(countsValue?.value, depositProgramValue.type));
                        }
                    }
                }}/>
            </Form.Item>

            <Form.Item label={'End deposit program'}>
                <DatePicker value={endDateValue} disabled={true}/>
            </Form.Item>
            <Form.Item label={'Amount money'}>
                <InputNumber
                    min={50}
                    max={2000000}
                    value={Number(amountMoney)}
                    parser={value => value ? value.replace(/\s?|(,*)/g, '') : '0'}
                    onChange={(value) => setAmountMoney(value as number)}
                />
            </Form.Item>

            <Form.Item {...tailLayout}>
                <Button type="primary" htmlType="submit">Save</Button>
            </Form.Item>
        </Form>
    )
})