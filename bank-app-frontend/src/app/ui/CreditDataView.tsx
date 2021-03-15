import React, {useContext} from 'react';
import {RootStoreContext} from 'app/RootStoreContext';
import {RouteComponentProps, useHistory} from 'react-router';
import {MatchId} from 'app/ui/EditUserComponent';
import moment from 'moment';
import {Button, Col, Row} from 'antd';

export const CreditDataView = (props: RouteComponentProps<MatchId>) => {
    const id = props.match.params.id;
    const {contracts} = useContext(RootStoreContext);

    const contract = contracts.items.find(st => st.contractNumber === id);

    const history = useHistory();

    if (!contract) {
        return <>Sorry not found!</>
    }

    let data = null;

    if (contract.creditName === 'Last') {
        const S = contract.amount;
        const r = contract.percents / 100;
        const n = moment(contract.endDate).diff(moment(contract.startDate), 'months');
        const P = S * ((r * (1 + r) ** n) / ((1 + r) ** n - 1));
        data = <Row>
            <Col span={24}>Every month Pay {P.toFixed(2)}</Col>
            <Col span={6}><strong>Percents</strong> {contract.percents}</Col>
            <Col span={6}><strong>Amount</strong> {contract.amount}</Col>
            <Col span={6}><strong>Count month</strong> {n}</Col>
        </Row>
    }
    if (contract.creditName === 'First') {
        const result: any[] = [];

        let money = contract.amount;
        const percents = contract.percents / 100;
        result.push((<><Row key={'header'}>
            <Col span={6}>
                Amount {money}
            </Col>
            <Col span={6}>
                Percents {percents}
            </Col>
            <Col span={6}>
                Start date {contract.startDate}
            </Col>
            <Col span={6}>
                End date {contract.endDate}
            </Col>
        </Row>
            <Row key={'header table'}>
                <Col span={12}>
                    <strong>Month Number</strong>
                </Col>
                <Col span={12}>
                    <strong>Pay</strong>
                </Col>
            </Row></>))
        const countMonth = moment(contract.endDate).diff(moment(contract.startDate), 'months');
        const payMonth = money / countMonth;
        for (let i = 1; i <= countMonth; i++) {
            const dayInMount = moment(contract.startDate).add(i - 1, 'd').daysInMonth();
            const commisia = (money * (percents) * dayInMount) / 365;
            const payMonthWithPercents = payMonth + commisia;
            result.push(<Row key={'body table' + i}>
                <Col span={12}>
                    {i} month
                </Col>
                <Col span={12}>
                    {payMonthWithPercents.toFixed(2)}
                </Col>
            </Row>)
            money = money - payMonth;
        }
        data = result;
    }
    return (
        <> <Button onClick={() => history.goBack()}>
            {'<'}
        </Button>{data}</>)
}