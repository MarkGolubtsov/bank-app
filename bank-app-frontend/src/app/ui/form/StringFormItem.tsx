import React from 'react';
import {Form, Input} from 'antd';
import {getRequireRule, getWhiteSpaceRule} from 'app/ui/form/rules';

interface DefaultStringFormItemProps {
    name: string | (string | number)[],
    shortName?: string,
    isNotRequire?: boolean
}

export const StringFormItem = ({name, shortName, isNotRequire}: DefaultStringFormItemProps) => {
    const nameString = Array.isArray(name) ? (shortName ?? name[2] as string) : (shortName ?? name as string);
    const label = nameString[0].toUpperCase() + nameString.slice(1, nameString.length);
    const rules = !isNotRequire ? [getWhiteSpaceRule(nameString), getRequireRule(nameString)] : [getWhiteSpaceRule(nameString)];
    return (
        <Form.Item rules={rules} name={name}
                   label={label}>
            <Input placeholder={'Write ' + nameString}/>
        </Form.Item>
    )
}