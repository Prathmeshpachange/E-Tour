using Microsoft.EntityFrameworkCore;
using SafarnamaApplication.Models;

namespace SafarnamaApplication.Repositories
{
    public class CostRepository : ICostRepository
    {
        private readonly SafarnamadbContext _context;

        public CostRepository(SafarnamadbContext context)
        {
            _context = context;
        }

        public async Task<Cost?> GetByPackageIdAsync(int packageId)
        {
            return await _context.Costs
                                 .FirstOrDefaultAsync(c => c.PackageId == packageId);
        }
    }
}
