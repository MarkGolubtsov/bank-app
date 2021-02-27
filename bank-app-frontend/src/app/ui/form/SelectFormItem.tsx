import React from 'react';
import {SelectValue} from 'app/entity/SelectValue';
import {observer} from 'mobx-react-lite';
import {Form, Select} from 'antd';
import {getRequireRule} from 'app/ui/form/rules';

interface SelectFormItemProps {
    items: SelectValue[],
    name: string | (string | number)[],
    label: string
}

export const SelectFormItem = observer(({items, label, name}: SelectFormItemProps) => {
    return (
        <Form.Item name={name} label={label} rules={[getRequireRule(label)]}>
            <Select>
                {
                    items.map(({_id, name}) => {
                        return <Select.Option key={_id} value={_id}>{name}</Select.Option>
                    })
                }
            </Select>
        </Form.Item>
    )
})