import React from 'react';
import {Form, Input} from 'antd';
import {getRequireRule, getWhiteSpaceRule} from 'app/ui/form/rules';

interface DefaultStringFormItemProps {
    name: string
}

export const StringFormItem = ({name}: DefaultStringFormItemProps) => {
    const label = name[0].toUpperCase() + name.slice(1, name.length);
    return (
        <Form.Item rules={[getWhiteSpaceRule(name), getRequireRule(name)]} name={name}
                   label={label}>
            <Input placeholder={'Write ' + name}/>
        </Form.Item>
    )
}