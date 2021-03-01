import React from 'react';
import {observer} from 'mobx-react-lite';
import {Form, Select} from 'antd';
import {getRequireRule} from 'app/ui/form/rules';

interface SelectFormItemProps {
    items: string[],
    name: string | (string | number)[],
    label: string
}

export const SelectFormItem = observer(({items, label, name}: SelectFormItemProps) => {
    return (
        items ? <Form.Item name={name} label={label} rules={[getRequireRule(label)]}>
            <Select>
                {
                    items.map((value) => {
                        return <Select.Option key={value} value={value}>{value}</Select.Option>
                    })
                }
            </Select>
        </Form.Item>: null
    )
})