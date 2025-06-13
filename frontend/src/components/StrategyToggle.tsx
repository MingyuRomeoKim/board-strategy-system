import React from 'react';
import {
    AppBar,
    Toolbar,
    Typography,
    ToggleButtonGroup,
    ToggleButton,
} from '@mui/material';

type StrategyType = 'paging' | 'infinite';

interface StrategyToggleProps {
    strategy: StrategyType;
    onChange: (strategy: StrategyType) => void;
}

const StrategyToggle: React.FC<StrategyToggleProps> = ({ strategy, onChange }) => {
    const handleChange = (_: React.MouseEvent<HTMLElement>, newStrategy: StrategyType | null) => {
        if (newStrategy !== null) {
            onChange(newStrategy);
        }
    };

    return (
        <AppBar position="sticky" color="primary">
            <Toolbar sx={{ justifyContent: 'space-between' }}>
                <Typography variant="h6" component="div">
                    게시판
                </Typography>
                <ToggleButtonGroup
                    value={strategy}
                    exclusive
                    onChange={handleChange}
                    color="secondary"
                    size="small"
                >
                    <ToggleButton value="paging">페이징</ToggleButton>
                    <ToggleButton value="infinite">무한스크롤</ToggleButton>
                </ToggleButtonGroup>
            </Toolbar>
        </AppBar>
    );
};

export default StrategyToggle;
