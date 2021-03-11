import React, {useContext, useEffect, useState} from 'react';
import {Button, DatePicker, Form, Input, InputNumber, notification, Select} from 'antd';
import moment from 'moment';
import {RootStoreContext} from 'app/RootStoreContext';
import {observer} from 'mobx-react-lite';

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

const creditesPlans: DepType[] = [
    {
        id: 1,
        name: 'First',
        countDays: [
            {
                value: 6,
                procent: 29.9
            },
            {
                value: 12,
                procent: 29.9
            },
            {
                value: 18,
                procent: 29.9
            },
            {
                value: 24,
                procent: 29.9
            },
            {
                value: 36,
                procent: 29.9
            },
            {
                value: 48,
                procent: 29.9
            },
            {
                value: 60,
                procent: 29.9
            }
            ],
        type: 'months'
    },
    {
        id: 2,
        name: 'Last',
        countDays: [{
            value: 48,
            procent: 28.9
        }, {
            value: 60,
            procent: 28.9
        }],
        type: 'months'
    }
];

type CreditAgreementFormPops = {
    onCreate: (data: any) => void;
};

interface map {
    [key: string]: string
}

export const CreditAgreementForm = observer(({onCreate}: CreditAgreementFormPops) => {
    const {currency} = useContext(RootStoreContext);

    useEffect(() => {
        currency.fetchItems();
    }, [currency]);
    const [crediteProgramValue, setCreditProgramValue] = useState<DepType | undefined>(undefined);
    const [currencyValue, setCurrencyValue] = useState<string | undefined>(undefined);
    const [startDateValue, setStartDateValue] = useState<moment.Moment>(moment());
    const [endDateValue, setEndDateValue] = useState<moment.Moment>(moment().add(1, 'd'));

    const [countsValue, setCountValue] = useState<CountType | undefined>(undefined);
    const [amountMoney, setAmountMoney] = useState<number>(50);
    const creditePrograms: DepType[] = creditesPlans;

    const tailLayout = {
        wrapperCol: {offset: 8, span: 16},
    };

    const onSave = () => {
        if (crediteProgramValue?.id && currencyValue && currency && countsValue) {
            const credite = {
                programId: crediteProgramValue?.id,
                currency: currencyValue,
                startDate: startDateValue.toDate(),
                endDate: endDateValue.toDate(),
                percents: countsValue.procent,
                amount: amountMoney
            }
            onCreate(credite);
        } else {
            notification.error({
                message: 'Check values',
                placement: 'bottomLeft'
            })
        }

    }

    const countDays: CountType[] = crediteProgramValue?.countDays ?? [];

    const handleChangeDepProg = (value?: DepType) => {
        setCreditProgramValue(value);
        const countDay = value?.countDays[0];
        setCountValue(countDay);
        if (value && countDay) {
            setEndDateValue(startDateValue.clone().add(countDay.value, value.type));
        }
    };

    const handleChangeCountsDayValue = (value: number) => {
        setCountValue(crediteProgramValue?.countDays.find(c => c.value == value));
        if (crediteProgramValue) {
            setEndDateValue(startDateValue.clone().add(value, crediteProgramValue.type));
        }
    };

    const countMessageType = crediteProgramValue ? crediteProgramValue?.type === 'months' ? 'month' : 'days' : '';
    return (
        <Form
            labelCol={{span: 4}}
            wrapperCol={{span: 10}}
            layout="horizontal"
            size={'large'}
            onFinish={onSave}
        >
            <Form.Item label={'Credit program'} help={crediteProgramValue ? '' : 'Choose credit program'}
                       validateStatus={crediteProgramValue ? 'success' : 'error'}>
                <Select
                    onChange={(value) => handleChangeDepProg(creditePrograms.find(dep => dep.id === Number(value)))}
                    value={crediteProgramValue?.name}>
                    {
                        creditePrograms.map((value) => {
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

            <Form.Item label={'Credit interest:'}>
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

            <Form.Item label={'Start credit program'}>
                <DatePicker value={startDateValue} onChange={(date) => {
                    if (date) {
                        setStartDateValue(date);
                        if (crediteProgramValue && countsValue) {
                            setEndDateValue(date.clone().add(countsValue?.value, crediteProgramValue.type));
                        }
                    } else {
                        setStartDateValue(moment());
                        if (crediteProgramValue && countsValue) {
                            setEndDateValue(moment().clone().add(countsValue.value, crediteProgramValue.type));
                        }
                    }
                }}/>
            </Form.Item>

            <Form.Item label={'End credit program'}>
                <DatePicker value={endDateValue} disabled={true}/>
            </Form.Item>
            <Form.Item label={'Amount money'}>
                <InputNumber
                    min={50}
                    max={15000}
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