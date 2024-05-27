import { createUseStyles } from '@mui/styles';

const useStyles = createUseStyles({
  tableHeaderCell: {
    colSpan: 2,
    textAlign: 'center',
  },
});

export default function useBalanceStyles() {
  const classes = useStyles();
  return classes;
}
